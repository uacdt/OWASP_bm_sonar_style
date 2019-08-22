package org.owasp.benchmark.testcode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class OrganizeFiles {
  public static void main(String[] args) {
    InputStream stream = OrganizeFiles.class.getClassLoader().getResourceAsStream("expectedresults-1.2.csv");
    Scanner scanner = new Scanner(stream);
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      if (!line.startsWith("#")) {
        String[] data = line.split(",");

        String fileName = data[0];
        String category = data[1];

        createCategoryDirectories(category);

        boolean issueExpected = "true".equals(data[2]);

        try {
          System.out.println(fileName);
          if (issueExpected) {
            Files.move(new File("src/main/java/org/owasp/benchmark/testcode/", fileName + ".java").toPath(),
              new File("src/main/java/org/owasp/benchmark/testcode/" + category + "/issueexpected/", fileName + ".java").toPath(), REPLACE_EXISTING);
          } else {
            Files.move(new File("src/main/java/org/owasp/benchmark/testcode/", fileName + ".java").toPath(),
              new File("src/main/java/org/owasp/benchmark/testcode/" + category + "/noissueexpected/", fileName + ".java").toPath(), REPLACE_EXISTING);
          }
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }

      }
    }
    scanner.close();
  }

  private static void createCategoryDirectories(String category) {
    File compliantCategoryDir = new File("src/main/java/org/owasp/benchmark/testcode/" + category + "/compliant/");
    if (!compliantCategoryDir.exists()) {
      compliantCategoryDir.mkdirs();
    }

    File noncompliantCategoryDir = new File("src/main/java/org/owasp/benchmark/testcode/" + category + "/noncompliant/");
    if (!noncompliantCategoryDir.exists()) {
      noncompliantCategoryDir.mkdirs();
    }
  }
}
