package via.dk;

import io.grpc.stub.StreamObserver;
import via.dk.HelloRequest;
import via.dk.HelloResponse;
import via.dk.HelloServiceGrpc;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
	@Override
	public void hello(
			HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

		String greeting = "Hello, " +
				request.getFirstName() +
				" " +
				request.getLastName();

		HelloResponse response = HelloResponse.newBuilder()
				.setGreeting(greeting)
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
