----------------------------------------------------
How to run the application:                        |
----------------------------------------------------

Requirements:
  - A machine with:
     - Maven +3.X installed
     - Java VM 1.8
     - A directory with write permits. Some files will be created in the directory at execution time.

0. Decompress the file in a suitabled directory.

1. In a terminal/console execute next commands:
> cd swrve

2. Create the jar:
> mvn clean install

   It will create the ./target directory


3. Running the application:

3.1.  Running using java jar
> java -jar ./target/swrve-1.0.jar --url.from.file=url

e.g.
>java -jar ./target/swrve-1.0.jar --url.from.file=https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz

3.2. Alternatively to point 3.1, running using spring boot. But the output will be more verbose.
>  mvn spring-boot:run -Dspring-boot.run.arguments=url

e.g.
> mvn spring-boot:run -Dspring-boot.run.arguments=https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz


--------------------------------------------------------------------------------------------------

Notes:
- "returning a non-zero exit status".
  I believe the document requires it to be able to interact with external tools. But It prevents me to reach 100% coverage. In Java exit code different to zero makes the VM stop abruptaly preventing the executing of the remaining unit tests. So i have to ignore some useful tests.

- I guessed that the "spend" column in the CSV is a "long type" just by looking the example given. So i assume that the "spend" value doesn't have decimals. Otherwise my application couldn't match the output in the document.

- "user_id of the first user who joined".
  Without more information about the application context. I guess that the doc is asking for the first user_id field of the first valid row in the csv.

