package br.com.baz.resources


import br.com.baz.ProductServiceRequest
import br.com.baz.ProductServiceResponse
import br.com.baz.ProductsServiceGrpc
import br.com.baz.dto.ProductReq
import br.com.baz.exceptions.BaseBusinessException
import br.com.baz.services.ProductService
import br.com.baz.utils.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductResources(
    private val productService: ProductService
) : ProductsServiceGrpc.ProductsServiceImplBase() {

    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
            val payload = ValidationUtil.validatePayload(request)
            val productReq = ProductReq(
                name = payload.name,
                price = payload.price,
                quantity_in_stock = payload.quantityInStock
            )
            val productRes = productService.create(productReq)
            val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setQuantityInStock(productRes.quantity_in_stock)
                .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        }catch (ex: BaseBusinessException) {
            responseObserver?.onError(ex.statusCode().toStatus()
                .withDescription(ex.errorMessage()).asRuntimeException()
            )
        }
    }
}