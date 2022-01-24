package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {
  Issue selectIssue;
  public boolean isIssueOpen(int issueId) throws IOException {
    String issueById = getExecutor()
            .execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(issueById);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> getIssues= new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    selectIssue = getIssues.iterator().next();
    if (selectIssue.getStatusIssue().equals("Resolved") || selectIssue.getStatusIssue().equals("Closed")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    System.out.println((isIssueOpen(issueId)));
    System.out.println("Id = " + issueId + " Status: " + selectIssue.getStatusIssue());
    if (isIssueOpen(issueId)) {
      System.out.println("Ignored because of issue status " + selectIssue.getStatusIssue());
      throw new SkipException("Ignored because of issue " + issueId);
    } else {
      System.out.println("Issue created!");
    }
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }
}
