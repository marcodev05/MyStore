package com.tsk.ecommerce.services.orderLine;


import com.tsk.ecommerce.services.ObjectFinder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.OrderLineRepository;
import com.tsk.ecommerce.repositories.ProductRepository;
import com.tsk.ecommerce.exceptions.BadRequestException;

@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ProductRepository productRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderLine create(OrderLine orderLine) {
        OrderLine ordln = new OrderLine();
        ordln.setQuantity(orderLine.getQuantity());
        if (orderLine.getProduct() != null) {
            Product p = ObjectFinder.findById(productRepository, "product", orderLine.getProduct().getIdProduct());
            ordln.setProduct(p);
            Double pu = p.getPrice();

            if (p.getAvailable()) {
                if (p.getStock() >= orderLine.getQuantity()) {
                    Integer orderQty = orderLine.getQuantity();
                    Double subtotal = pu * orderQty;
                    ordln.setSubTotal(subtotal);

//					Integer restStock = p.getStock() - orderLine.getQuantity();
//					p.setStock(restStock);
//					if (restStock == 0)
//						p.setAvailable(false);
//					productRepository.save(p);

                } else
                    throw new BadRequestException("Le stock est insuffisant, reste: " + p.getStock());

            } else
                throw new ResourceNotFoundException("Ce produit est indisponible !");

        } else
            throw new BadRequestException(" Il faut préciser le produit!");


        return orderLineRepository.save(ordln);
    }

    @Override
    public OrderLine update(Long id, OrderLine orderLine) {
        return null;
    }


    @Override
    public OrderLine getOrderLineById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de commande introuvable"));
    }


    @Override
    public void deleteOrderLine(Long id) {
        OrderLine ordln = this.getOrderLineById(id);
        Product product = ordln.getProduct();
        Integer stockProduct = product.getStock();

        Integer ordQte = ordln.getQuantity();
        product.setStock(stockProduct + ordQte);
        if (product.getStock() > 0) {
            product.setAvailable(true);
        }
        productRepository.save(product);
        orderLineRepository.delete(ordln);
    }

}
