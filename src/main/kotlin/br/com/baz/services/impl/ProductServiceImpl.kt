package br.com.baz.services.impl

import br.com.baz.dto.ProductReq
import br.com.baz.dto.ProductRes
import br.com.baz.exceptions.AlreadyExistsException
import br.com.baz.exceptions.ProductNotFoundException
import br.com.baz.repository.ProductRepository
import br.com.baz.services.ProductService
import br.com.baz.utils.toDomain
import br.com.baz.utils.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService{
    override fun create(req: ProductReq): ProductRes {
        verifyName(req.name)
        val productSaved = productRepository.save(req.toDomain())
        return productSaved.toProductRes()
    }

    override fun findById(id: Long): ProductRes {
        val findById = productRepository.findById(id)
        findById.orElseThrow { ProductNotFoundException(id) }
        return findById.get().toProductRes()
    }

    private fun verifyName(name: String) {
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }
}