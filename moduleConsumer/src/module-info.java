module moduleConsumer {
  requires content;
  // requires transitive content;

  exports consumer;

  uses modules.exposed.ExposedInterface;
}