gen:
	protoc --proto_path=proto \
	--go_out=paths=source_relative:pb \
	proto/*.proto

clean:
	rm pb/*.go

run:
	go run main.go

test:
	go test -cover -race ./...