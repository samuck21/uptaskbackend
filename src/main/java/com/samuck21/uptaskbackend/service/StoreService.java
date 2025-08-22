package com.samuck21.uptaskbackend.service;

import com.samuck21.uptaskbackend.dto.user.*;
import com.samuck21.uptaskbackend.models.Store;
import com.samuck21.uptaskbackend.models.Task;
import com.samuck21.uptaskbackend.repositories.StoreRepository;
import com.samuck21.uptaskbackend.repositories.TaskRepository;
import com.samuck21.uptaskbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public CreateStoreResponse create(CreateStoreRequest request) {


        Store store = new Store();
        store.setDescription(request.description);
        store.setStatus(request.status);
        store.setModel(request.model);
        store.setCodemd(request.codemd);
        store.setSupplier(request.supplier);
        store.setMarket(request.market);
        store.setCategory(request.category);
        store.setPriceunitwoiva(request.priceunitwoiva);
        store.setMinstock(request.minstock);
        store.setMultiplebuy(request.multiplebuy);
        store.setMaxstock(request.maxstock);
        store.setNeedpermouth(request.needpermouth);
        store.setStock(request.stock);
        store.setUnits(request.units);
        store.setEnd(request.end);
        store.setCoststock(request.coststock);
        store.setDeparment(request.deparment);


        Store saveStore = storeRepository.save(store);

        CreateStoreResponse response = new CreateStoreResponse();
        response.setId(Long.valueOf(saveStore.getId()));
        response.setDescription(saveStore.getDescription());
        response.setStatus(saveStore.getStatus());
        response.setModel(saveStore.getModel());
        response.setCodemd(saveStore.getCodemd());
        response.setSupplier(saveStore.getSupplier());
        response.setMarket(saveStore.getMarket());
        response.setCategory(saveStore.getCategory());
        response.setPriceunitwoiva(saveStore.getPriceunitwoiva());
        response.setMinstock(saveStore.getMinstock());
        response.setMultiplebuy(saveStore.getMultiplebuy());
        response.setMaxstock(saveStore.getMaxstock());
        response.setNeedpermouth(saveStore.getNeedpermouth());
        response.setUnits(saveStore.getUnits());
        response.setEnd(saveStore.getEnd());
        response.setCoststock(saveStore.getCoststock());
        response.setStock(saveStore.getStock());
        response.setDeparment(saveStore.getDeparment());



        return response;
    }


    @Transactional
    public CreateStoreResponse update(Long id, UpdateStoreRequest request) throws IOException {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe esa tarea"));

        store.setDescription(request.description);
        store.setStatus(request.status);
        store.setModel(request.model);
        store.setCodemd(request.codemd);
        store.setSupplier(request.supplier);
        store.setMarket(request.market);
        store.setCategory(request.category);
        store.setPriceunitwoiva(request.priceunitwoiva);
        store.setMinstock(request.minstock);
        store.setMultiplebuy(request.multiplebuy);
        store.setMaxstock(request.maxstock);
        store.setNeedpermouth(request.needpermouth);
        store.setStock(request.stock);
        store.setUnits(request.units);
        store.setEnd(request.end);
        store.setCoststock(request.coststock);
        store.setDeparment(request.deparment);

        if (request.getDescription() != null) {
            store.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            store.setStatus(request.getStatus());
        }
        if (request.getModel() != null) {
            store.setModel(request.getModel());
        }
        if (request.getCodemd() != null) {
            store.setCodemd(request.getCodemd());
        }
        if (request.getMarket() != null) {
            store.setMarket(request.getMarket());
        }
        if (request.getCategory() != null) {
            store.setCategory(request.getCategory());
        }

        if (request.getPriceunitwoiva() > 0) {
            store.setPriceunitwoiva(request.getPriceunitwoiva());
        }

        if (request.getMinstock() > 0) {
            store.setMinstock(request.getMinstock());
        }

        if (request.getMultiplebuy() > 0) {
            store.setMultiplebuy(request.getMultiplebuy());
        }
        if (request.getMaxstock() > 0) {
            store.setMaxstock(request.getMaxstock());
        }
        if (request.getNeedpermouth() > 0) {
            store.setNeedpermouth(request.getNeedpermouth());
        }
        if (request.getStock() > 0) {
            store.setStock(request.getStock());
        }
        if (request.getUnits() != null) {
            store.setUnits(request.getUnits());
        }
        if (request.getEnd() > 0) {
            store.setEnd(request.getEnd());
        }
        if (request.getCoststock() > 0) {
            store.setCoststock(request.getCoststock());
        }
        if (request.getDeparment() != null) {
            store.setDeparment(request.getDeparment());
        }



        storeRepository.save(store);

        store.setDescription(request.description);
        store.setStatus(request.status);
        store.setModel(request.model);
        store.setCodemd(request.codemd);
        store.setSupplier(request.supplier);
        store.setPriceunitwoiva(request.priceunitwoiva);
        store.setStock(request.stock);

        CreateStoreResponse createStoreResponse = new CreateStoreResponse();
        createStoreResponse.setDescription(store.getDescription());
        createStoreResponse.setStatus(store.getStatus());
        createStoreResponse.setModel(store.getModel());
        createStoreResponse.setCodemd(store.getCodemd());
        createStoreResponse.setSupplier(store.getSupplier());
        createStoreResponse.setPriceunitwoiva(store.getPriceunitwoiva());
        createStoreResponse.setStock(store.getStock());

        return createStoreResponse;
    }

    @Transactional
    public List<Store> readAll(){
        return storeRepository.findAll();

    }

    @Transactional
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}
