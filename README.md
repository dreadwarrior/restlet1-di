# Restlet 1.1.x example app with Dependency Injection

## Synopsis

When working with a legacy Restlet 1.1.x application, it may become difficult to
establish automated tests because no dependency injection is in place.

By getting rid of statically instantiated classes and using integration testing,
it should become easier to reason about the application, reducing its overall 
complexity and improving its architecture.

## Motivation

While [Restlet 2.x comes with an extension for using Google Guice for dependency
injection](https://restlet.talend.com/documentation/user-guide/2.4/extensions/guice) 
in `Resource`s, Restlet version 1.x comes with no such functionality
out of the box.

The approach should be very defensive and should allow minimal effort for 
integrating DI to the existing application because of missing test coverage.

## Approach

*Production call chain*

The Guice dependency module is instantiated very early in the application 
lifecycle in the class holding the `main` method and passed to the Restlet 
application class. See [App](src/main/java/de/dreadlabs/App.java#L15).

The Restlet application uses a derived Restlet `Router` to hook into the 
instantiation of attached `Resources` by using a derived Restlet `Finder`. Both
derived components are "bridging" Guice to Restlet, to make use of the Guice 
`Injector` APIs. See
[ExampleRestletApplication](src/main/java/de/dreadlabs/ExampleRestletApplication.java),
[GuiceRouter](src/main/java/de/dreadlabs/infrastructure/restletguicebridge/GuiceRouter.java)
and [GuiceFinder](src/main/java/de/dreadlabs/infrastructure/restletguicebridge/GuiceFinder.java).

The derived `Finder` is delegating the `Resource` initialization from 
constructor call (as the built-in Restlet `Finder` does) to the `Resource#init()`
method (which is additionally used by the built-in Restlet `Finder`).

---

**Note**

This approach may be difficult to integrate, if the existing `Resources` of the
legacy application use a complex inheritance hierarchy, when their [constructors
are not code-free](https://www.yegor256.com/2015/05/07/ctors-must-be-code-free.html) 
or simply "do too much".

---

*Test call chain*

It's now possible to introduce an (integration) base test class which handles
starting and stopping the Restlet application. An abstract method allows 
use-case- or test-specific dependency configuration. See [IntegrationTest](src/test/java/de/dreadlabs/IntegrationTest.java) and [HelloWorldTest](src/test/java/de/dreadlabs/examplefeature/HelloWorldTest.java).

A more sophisticated approach, by using JUnit initializer tooling, would be 
possible. This would allow better control for _arrange_ steps in specific test
methods.

This was left out for brevity and for future exercises. :blush:

## Outlook

1. Analyse runtime impacts of derived `Finder` instantiation.
2. Analyse refactoring efforts on complex `Resource` inheritance hierarchies.
3. Inspect JUnit initializer tooling for fine-grained control for arrange steps.
4. Analyse side effects to other `Restlet` components, like `Filter` etc.
