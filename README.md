## Compose serialization issue

```
./gradlew :without-compose:run
# OK

./gradlew :with-compose:run
# java.lang.IllegalAccessError: Update to non-static final field AccountInfo$Details.fullName attempted from a different method (access$setFullName$p) than the initializer method <init>
```