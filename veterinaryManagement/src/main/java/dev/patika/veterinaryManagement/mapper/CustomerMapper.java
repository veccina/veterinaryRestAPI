package dev.patika.veterinaryManagement.mapper;

import dev.patika.veterinaryManagement.dto.request.CustomerRequest;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerResponse asOutput(Customer customer);
    List<CustomerResponse> asOutput(List<Customer> customer);
    Customer asEntity(CustomerRequest customerRequest);

    void update(@MappingTarget Customer entity, CustomerRequest request);
}
