# User Record Parser

## Description

Reads an input json file for records of registered users.

Includes sparse test coverage.

### Build

Compile with maven

```
mvn clean compile assembly:single
```

which generates a jar with dependencies in "target" directory. Run with

```
java -jar record-reader-0.1-jar-with-dependencies.jar <PATH_TO_JSON> <BATCH_SIZE>
```

replacing PATH_TO_JSON with the path to input file and optionally, BATCH_SIZE to specify the size of each batch read.

### Details

A record file is parsed in fixed-size batches using Gson for json deserialization and sent through some pre-defined queries.

Queries are defined with two parts: zero or more "filters" which filter out records not looked for by the query, and one "stats collector" which reads the relevant data from the incoming records. A new query can be defined with a type of stats collector and added to ReaderMain.

After every batch, and at the end, the results of each query is printed to std out.

## Assumptions / Design decisions

Some low-level design decisions are addressed as comments in-line.

### Queries are pre-defined

Queries are assumed to be defined at compile time, not run time. This way not all the data need be loaded at once. For instance, calculating the mean of some value only requires keeping a running sum of the value across all records parsed so far. If, after some portion of records are parsed, a new query is needed to be defined, the query will only act upon the portion of records not parsed yet.

If queries are needed to be loaded dynamically, we can implement some database instead, possibly in-memory. That way new queries can look at previously parsed records.

### No unexpected input

Input is well-formed, structured appropriately, and contains only expected entries (e.g. isActive always boolean, balance always begins with "$"). Otherwise we would need to do some validation, possibly through an intermediate layer between raw json and UserRecord object like a custom TypeAdapter.

We assume no overflow on integers and doubles.

There are no guards against invalid input.

### Pretty print

There is only basic pretty-printing of each batch.
