package main

import (
 "context"
 pb "grpc-go/proto"
 "google.golang.org/grpc"
 "log"
 "net"
)

type server struct {
    pb.UnimplementedHelloServiceServer
}

    func (s *server) Hello(ctx context.Context, in *pb.HelloRequest) (*pb.HelloResponse, error) {
        greeting := "Hello, " + in.FirstName + " " + in.LastName + "!"
 return &pb.HelloResponse{Greeting: greeting}, nil
}

func main() {
 lis, err := net.Listen("tcp", ":8080")
 if err != nil {
  log.Fatalf("failed to listen on port 50051: %v", err)
 }
 log.Printf("gRPC server listening at %v", lis.Addr())

 s := grpc.NewServer()
 pb.RegisterHelloServiceServer(s, &server{})
 log.Printf("gRPC server listening at %v", lis.Addr())
 if err := s.Serve(lis); err != nil {
  log.Fatalf("failed to serve: %v", err)
 }
}
