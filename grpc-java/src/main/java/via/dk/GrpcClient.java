package via.dk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.dk.HelloRequest;
import via.dk.HelloResponse;
import via.dk.HelloServiceGrpc;

public class GrpcClient {
	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
				.usePlaintext()
				.build();

		HelloServiceGrpc.HelloServiceBlockingStub stub
				= HelloServiceGrpc.newBlockingStub(channel);

		HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
				.setFirstName("Dimitar")
				.setLastName("Nizamov")
				.build());

		System.out.println(helloResponse.getGreeting());

		channel.shutdown();
	}
}