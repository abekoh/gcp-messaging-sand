# gcp-messaging-sand-twitter-source
secret作成方法
```
kubectl create secret generic gcp-messaging-sand-twitter-source --from-file secret/application-secret.yml --from-file secret/spring-pubsub.json -n messaging -o yaml --dry-run > secret.yaml
```
