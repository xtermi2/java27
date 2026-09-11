[![Java CI](https://github.com/xtermi2/java27/actions/workflows/maven.yml/badge.svg)](https://github.com/xtermi2/java27/actions/workflows/maven.yml)

# Schedule

- 2026/08/20 Release Candidate Build
- 2026/09/15 General Availability

# JDK 27 Features

## [JEP 523: Make G1 the Default Garbage Collector in All Environments](https://openjdk.org/jeps/523)

- Make the Garbage-First (G1) garbage collector the default collector in all environments, rather than just server environments (The HotSpot JVM will always select G1 when no garbage collector is specified on the command line).
- In scenarios in which the JVM previously selected the Serial collector, the performance metrics of throughput, latency, memory footprint, and startup time should not degrade significantly.

## [JEP 527: Post-Quantum Hybrid Key Exchange for TLS 1.3](https://openjdk.org/jeps/527)

- Enhance the security of Java applications that require secure network communication by implementing hybrid key exchange algorithms for TLS 1.3.
- These 3 are implemented:
  - `X25519MLKEM768`: Hybrid scheme combining ECDHE with X25519 and ML-KEM-768
  - `SecP256r1MLKEM768`: Hybrid scheme combining ECDHE using the secp256r1 curve with ML-KEM-768
  - `SecP384r1MLKEM1024`: Hybrid scheme combining ECDHE using the secp384r1 curve with ML-KEM-1024
- see example `PostQuantumHybridKeyExchange.java`

## [JEP 531: Lazy Constants (Third Preview)](https://openjdk.org/jeps/531)

- Introduce an API for lazy constants, which are objects that hold unmodifiable data. Lazy constants are treated as true constants by the JVM, enabling the same performance optimizations that are enabled by declaring a field final. Compared to final fields, however, lazy constants offer greater flexibility as to the timing of their initialization.
- Changes since the second preview in JDK 26:
  - Remove the low-level methods `isInitialized` and `orElse`.
  - Add a new factory method, `Set.ofLazy(...)`, that can create a stable Set of pre-defined element candidates. With this addition, there will be lazy versions of the three fundamental collection types: List, Set, and Map.
- see example `LazyConstant.java`

## [JEP 532: Primitive Types in Patterns, instanceof, and switch (Fifth Preview)](https://openjdk.org/jeps/532)

- Enhance pattern matching by allowing primitive type patterns in all pattern contexts, and extend instanceof and
  switch to work with all primitive types.
- No changes since last preview in JDK 26.
- see example `PrimitiveTypesInPatterns.java`

## [JEP 533: Structured Concurrency (Seventh Preview)](https://openjdk.org/jeps/533)

- Simplify concurrent programming by introducing an API for structured concurrency. Structured concurrency treats
  groups of related tasks running in different threads as a single unit of work, thereby streamlining error handling
  and cancellation, improving reliability, and enhancing observability.
- API changes since last preview in JDK 26:
  - The StructuredTaskScope and Joiner interfaces now have a third type parameter, R_X, for the type of the exception that the join() method of StructuredTaskScope can throw.
  - A new static open method in StructuredTaskScope implements the default join policy and uses a given UnaryOperator to produce the StructuredTaskScope configuration.
  - The Joiner factory methods allSuccessfulOrThrow(), anySuccessfulOrThrow(), and awaitAllSuccessfulOrThrow() now create joiners that cause join() to throw an ExecutionException when the outcome is an exception. New overloads of the three methods allow a Function to be specified to produce a different exception.
  - The Joiner factory method awaitAll() has been removed.
  - The onTimeout() method of the Joiner interface has been replaced by the timeout() method, which either produces the result or throws an exception when the scope is cancelled by a timeout. If the timeout() method throws an exception then the exception is thrown with a CancelledByTimeoutException as the cause.
- see example `StructuredConcurrency.java`

## [JEP 534: Compact Object Headers by Default](https://openjdk.org/jeps/534)

- Make compact object headers the default object header layout in the HotSpot JVM.
- No need to set `-XX:+UseCompactObjectHeaders` to enable compact object headers.
  - can be disabled via `-XX:-UseCompactObjectHeaders`

## [JEP 536: JFR In-Process Data Redaction](https://openjdk.org/jeps/536)

- Enhance JDK Flight Recorder (JFR) to redact command-line arguments and the initial values of environment variables and system properties in recordings. Redact this data before it leaves the process, so that sensitive information does not leak.
- JFR will now redact many kinds of sensitive information by default, without any additional configuration.
- Configuration options:
  - The `redact-argument` sub-option specifies a filter list for command-line arguments. If one of the filters matches an argument, the argument is redacted.
  - The `redact-key` sub-option specifies a filter list for key-value pairs in the form of environment variables and system properties. If one of the filters matches a key, the associated value is redacted.

## [JEP 537: Vector API (Twelfth Incubator)](https://openjdk.org/jeps/537)

- Introduce an API to express vector computations that reliably compile at runtime to optimal vector instructions on supported CPUs, thus achieving performance superior to equivalent scalar computations.
- The Vector API will incubate until necessary features
  of [Project Valhalla](https://openjdk.org/projects/valhalla/) become available as preview features. At
  that time, the Vector API will be adapted and its implementation to use them, and will be promoted from incubation
  to preview.

## [JEP 538: PEM Encodings of Cryptographic Objects (Third Preview)](https://openjdk.org/jeps/538)

- Introduce an API for encoding objects that represent cryptographic keys, certificates, and certificate revocation
  lists into the widely-used Privacy-Enhanced Mail (PEM) transport format, and for decoding from that format back into
  objects.
- Support standards — Support conversions between PEM text and cryptographic objects that have standard representations
  in the binary formats PKCS#8 (for private keys), X.509 (public keys, certificates, and certificate revocation lists),
  and PKCS#8 v2.0 (encrypted private keys and asymmetric keys).
- Changes since the last preview in JDK 26:
  - The PEM class is now an ordinary class rather than a record.
  - The DEREncodable interface is now named BinaryEncodable.
  - The EncryptedPrivateKeyInfo class now includes getKeyPair methods that decrypt PKCS#8-encoded text containing a PublicKey.
  - The getKey and getKeyPair methods of EncryptedPrivateKeyInfo that took a password and Provider now take only a Key.
  - The withFactory method of PEMDecoder is now named withFactoriesOf to better describe that key and certificate factories are obtained from the given Provider.
  - A new CryptoException class indicates failures in cryptographic processing at runtime.
- see example `PEMTest.java`

----------------------

##### Other References

- https://openjdk.org/projects/jdk/27/ 
