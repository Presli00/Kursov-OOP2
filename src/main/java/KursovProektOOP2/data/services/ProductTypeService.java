package KursovProektOOP2.data.services;


import KursovProektOOP2.data.entity.ProductType;
import KursovProektOOP2.data.repository.ProductTypeRepository;

import java.util.List;

public class ProductTypeService {
    public final ProductTypeRepository repository = ProductTypeRepository.getInstance();

    public static ProductTypeService getInstance(){
        return ProductTypeService.ProductTypeServiceHolder.INSTANCE;
    }

    private static class ProductTypeServiceHolder{
        public static final ProductTypeService INSTANCE = new ProductTypeService();
    }

    public List<ProductType> getAllProductTypes(){
        return repository.getAll();
    }
}
