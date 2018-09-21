module consumer {
  requires provider;
  // requires transitive provider;

  exports consumer;

  uses provider.exposed.ExposedInterface;
}