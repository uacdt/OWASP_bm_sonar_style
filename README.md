# OWASP Benchmark Reorganized
This is a version of the OWASP Benchmark Project (Java) where all the test cases have been reorganized to make it easy for a human to explore it. Instead of having all the tests cases in a single directory:
- all the test cases are organized by categories: cmdi, crypto, hash, ldapi, pathtraver, securecookie, sqli, trustbound, weakrand, xpathi and xss
- on each category, the test cases are organized between:
-- "issueexpected" where an issue is expected
-- "notissueexpected" where an issue is not expected
- the "notissueexpected_discarded" directory is containing cases not covered by SonarQube Developer Edition because the engine is not yet ready or because we think the cases are not relevant in real life.
