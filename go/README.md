
## How to perform code generation
```
make gen
```

Add these lines in settings.json
```
"protoc": {
        "path": "/opt/homebrew/bin/protoc",
        "compile_on_save": false,
        "options": [
            "--proto_path=protos/v3",
            "--proto_path=protos/v2",
            "--proto_path=${workspaceRoot}/proto",
            "--proto_path=${env.GOPATH}/src",
            "--java_out=gen/java"
        ]
    }
```

## Miscellaneous
nginx config file is at `opt/homebrew/etc/nginx/nginx.conf`