package com.qa.hs.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class Login {

	public KeywordEngine keywordEngine;

	@Test
	public void loginTest() throws IOException {

		keywordEngine = new KeywordEngine();

		keywordEngine.startExecution("login");

	}
}
