package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ComputerDatabase extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.82 Safari/537.36")

	val uri1 = "https://o59183.ingest.sentry.io/api/5735937/envelope"

	val scn = scenario("ComputerDatabase")
		.exec(http("request_0")
			.get("/computers")
			
			.resources(http("request_1")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0001_request.txt")), 
				http("request_2")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0002_request.txt"))
			.check(status.is(429)),
				http("request_3")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0003_request.txt"))))
		.pause(5)
		.exec(http("request_4")
			.get("/computers/new")
			
			.resources(http("request_5")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0005_request.txt")),
				http("request_6")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0006_request.txt")),
				http("request_7")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0007_request.txt"))
			.check(status.is(429))))
		.pause(12)
		.exec(http("request_8")
			.post("/computers")
			
			.formParam("name", "Required1")
			.formParam("introduced", "1990-01-01")
			.formParam("discontinued", "2000-01-01")
			.formParam("company", "1")
			.resources(http("request_9")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0009_request.txt"))
			.check(status.is(429)),
				http("request_10")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0010_request.txt"))
			.check(status.is(429)),
				http("request_11")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0011_request.txt"))))
		.pause(5)
		.exec(http("request_12")
			.get("/computers?f=Required1")
			
			.resources(http("request_13")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0013_request.txt")),
				http("request_14")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0014_request.txt"))
			.check(status.is(429)),
				http("request_15")
			.post(uri1 + "/?sentry_key=f4e2effb36f24733942cf3c792d9d2a4&sentry_version=7")
			
			.body(RawFileBody("com/gatling/tests/computerdatabase/0015_request.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}