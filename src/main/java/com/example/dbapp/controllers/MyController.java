package com.example.dbapp.controllers;

import com.example.dbapp.repositories.CatalogueRepository;
import com.example.dbapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.dbapp.models.*;

import java.sql.Timestamp;


@Controller
public class MyController {

    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private ClientService clientService;

   @Autowired
    private CompanyService companyService;

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private OrderService orderService;

   @Autowired
    private ProviderService providerService;

   @Autowired
    private StorageService storageService;

   @Autowired
    private FactoryProviderService factoryProviderService;

    @Autowired
    private CatalogueRepository catalogueRepository;

    @RequestMapping(value = "/client" , method = RequestMethod.GET)
    public String client(Model model) {
        model.addAttribute("clients", clientService.getClients());
        model.addAttribute("client", new ClientEntity());
        return "client";
    }

    @RequestMapping(value = "/client" , method = RequestMethod.POST)
    public String createClient(@ModelAttribute("client") ClientEntity client, Model model) {
        if (client.getName().equals("") || client.getLicense().equals("") || client.getPhoneNumber().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("clients", clientService.getClients());
            return "client";
        }

        clientService.insertClient(client);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("clients", clientService.getClients());
        return "client";
    }
    @RequestMapping(value = "/deleteclient", method = RequestMethod.POST)
    public String deleteClient(@ModelAttribute("client") ClientEntity client, Model model, int clientId) {

        clientService.deleteById(clientId);
        model.addAttribute("clients", clientService.getClients());
        return "redirect:/client";

    }

    @RequestMapping(value = "/order" , method = RequestMethod.GET)
    public String order(Model model) {
       model.addAttribute("orders", orderService.getOrders());
       model.addAttribute("order", new OrderEntity());
       model.addAttribute("clients", clientService.getClients());
       model.addAttribute("client", new ClientEntity());
        return "order";
    }

    @RequestMapping(value = "/order" , method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") OrderEntity order, @ModelAttribute("client") ClientEntity client, Model model) {
        if (order.getSumm().equals("") || order.getClientByClientId().equals("") || order.getStatus().equals("") || order.getClientByClientId().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("orders", orderService.getOrders());
            return "order";
        }
        order.setDate(new Timestamp(System.currentTimeMillis()));
        orderService.insertOrder(order);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("clients", clientService.getClients());
        return "order";
    }
    @RequestMapping(value = "/deleteorder", method = RequestMethod.POST)
    public String deleteOrder(@ModelAttribute("order") OrderEntity order, Model model, int orderId) {

        orderService.deleteById(orderId);
        model.addAttribute("orders", orderService.getOrders());
        return "redirect:/order";

    }

    @RequestMapping(value = "/commentorder", method = RequestMethod.POST)
    public String changeComment(@ModelAttribute("order") OrderEntity order, @RequestParam(value = "comment1") String comment1, Model model) {

        orderService.updateComment(order.getId(), comment1);
        model.addAttribute("orders", orderService.getOrders());

        return "redirect:/order";

    }

    @RequestMapping(value = "/statusorder", method = RequestMethod.POST)
    public String changeStatus(@ModelAttribute("order") OrderEntity order, @RequestParam(value ="status1")  String status1, Model model) {

        orderService.updateStatus(order.getId(), status1);
        model.addAttribute("orders", orderService.getOrders());
        return "redirect:/order";

    }

    @RequestMapping(value = "/company" , method = RequestMethod.GET)
    public String company(Model model) {
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("company", new CompanyEntity());
        return "company";
    }

    @RequestMapping(value = "/filtercompany", method = RequestMethod.GET)
    public String filterCompanies(Model model, @RequestParam("workers1") int workers1, @RequestParam("workers2") int workers2){
        model.addAttribute("companies", companyService.filterCompanies(workers1, workers2));
        model.addAttribute("company", new CompanyEntity());
        return "company";
    }

    @RequestMapping(value = "/asccompany", method = RequestMethod.GET)
    public String ascCompanies(Model model){
        model.addAttribute("companies", companyService.ascCompanies());
        model.addAttribute("company", new CompanyEntity());
        return "company";
    }

    @RequestMapping(value = "/desccompany", method = RequestMethod.GET)
    public String descCompanies(Model model){
        model.addAttribute("companies", companyService.descCompanies());
        model.addAttribute("company", new CompanyEntity());
        return "company";
    }

    @RequestMapping(value = "/company" , method = RequestMethod.POST)
    public String createCompany(@ModelAttribute("company") CompanyEntity company, Model model) {
        if (company.getName().equals("") || company.getWorkers().equals("") || company.getAddress().equals("") || company.getDirector().equals("") || company.getIncome().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("companies", companyService.getCompanies());
            return "company";
        }
        company.setFounded(new Timestamp(System.currentTimeMillis()));
        companyService.insertCompany(company);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("companies", companyService.getCompanies());
        return "company";
    }
    @RequestMapping(value = "/deletecompany", method = RequestMethod.POST)
    public String deleteCompany(@ModelAttribute("company") CompanyEntity company, Model model, int companyId) {

        companyService.deleteById(companyId);
        model.addAttribute("companies", companyService.getCompanies());
        return "redirect:/company";

    }

    @RequestMapping(value = "/workerscompany", method = RequestMethod.POST)
    public String changeWorkersCompany(@ModelAttribute("company") CompanyEntity company, @RequestParam(value = "workers") Integer workers, Model model) {

        companyService.updateWorkers(company.getId(), workers);
        model.addAttribute("companies", companyService.getCompanies());

        return "redirect:/company";

    }

    @RequestMapping(value = "/incomecompany", method = RequestMethod.POST)
    public String changeIncomeCompany(@ModelAttribute("company") CompanyEntity company, @RequestParam(value ="income")  Integer income, Model model) {

        companyService.updateIncome(company.getId(), income);
        model.addAttribute("companies", companyService.getCompanies());
        return "redirect:/company";

    }

    @RequestMapping(value = "/factory" , method = RequestMethod.GET)
    public String factory(Model model) {
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        model.addAttribute("companies", companyService.getCompanies());
        model.addAttribute("company", new CompanyEntity());
        return "factory";
    }

    @RequestMapping(value = "/factory" , method = RequestMethod.POST)
    public String createFactory(@ModelAttribute("factory") FactoryEntity factory, @ModelAttribute("company") CompanyEntity company, Model model) {
        if (factory.getName().equals("") || factory.getCompanyByCompanyId().equals("") || factory.getProduct().equals("") || factory.getWorkers().equals("") || factory.getAddress().equals("") || factory.getDirector().equals("") || factory.getIncome().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("factories", factoryService.getFactories());
            return "factory";
        }
        factory.setFounded(new Timestamp(System.currentTimeMillis()));
        factoryService.insertFactory(factory);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("companies", companyService.getCompanies());
        return "factory";
    }
    @RequestMapping(value = "/deletefactory", method = RequestMethod.POST)
    public String deleteFactory(@ModelAttribute("factory") FactoryEntity factory, Model model, int factoryId) {

        factoryService.deleteById(factoryId);
        model.addAttribute("factories", factoryService.getFactories());
        return "redirect:/factory";

    }

    @RequestMapping(value = "/workersfactory", method = RequestMethod.POST)
    public String changeWorkersFactory(@ModelAttribute("factory") FactoryEntity factory, @RequestParam(value = "workers") Integer workers, Model model) {

        factoryService.updateWorkers(factory.getId(), workers);
        model.addAttribute("factories", factoryService.getFactories());

        return "redirect:/factory";

    }

    @RequestMapping(value = "/incomefactory", method = RequestMethod.POST)
    public String changeIncomeFactory(@ModelAttribute("factory") FactoryEntity factory, @RequestParam(value ="income")  Integer income, Model model) {

        factoryService.updateIncome(factory.getId(), income);
        model.addAttribute("factories", factoryService.getFactories());
        return "redirect:/factory";

    }

    @RequestMapping(value = "/provider" , method = RequestMethod.GET)
    public String provider(Model model) {
        model.addAttribute("providers", providerService.getProviders());
        model.addAttribute("provider", new ProviderEntity());
        return "provider";
    }

    @RequestMapping(value = "/provider" , method = RequestMethod.POST)
    public String createProvider(@ModelAttribute("provider") ProviderEntity provider, Model model) {
        if (provider.getName().equals("") || provider.getWorkers().equals("") || provider.getAddress().equals("") || provider.getDirector().equals("") || provider.getMaterial().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("providers", providerService.getProviders());
            return "provider";
        }
        providerService.insertProvider(provider);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("providers", providerService.getProviders());
        return "provider";
    }
    @RequestMapping(value = "/deleteprovider", method = RequestMethod.POST)
    public String deleteProvider(@ModelAttribute("provider") ProviderEntity provider, Model model, int providerId) {

        providerService.deleteById(providerId);
        model.addAttribute("providers", providerService.getProviders());
        return "redirect:/provider";

    }

    @RequestMapping(value = "/workersprovider", method = RequestMethod.POST)
    public String changeWorkersProvider(@ModelAttribute("provider") ProviderEntity provider, @RequestParam(value = "workers") Integer workers, Model model) {

        providerService.updateWorkers(provider.getId(), workers);
        model.addAttribute("providers", providerService.getProviders());

        return "redirect:/provider";

    }

    @RequestMapping(value = "/connection" , method = RequestMethod.GET)
    public String connection(Model model) {
        model.addAttribute("connections", factoryProviderService.getСonnections());
        model.addAttribute("connection", new FactoryProviderEntity());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        model.addAttribute("providers", providerService.getProviders());
        model.addAttribute("provider", new ProviderEntity());
        return "connection";
    }

    @RequestMapping(value = "/connection" , method = RequestMethod.POST)
    public String createConnection(@ModelAttribute("connection") FactoryProviderEntity connection, @ModelAttribute("factory") FactoryEntity factory, @ModelAttribute("provider") ProviderEntity provider, Model model) {
        if (connection.getFactoryByFactoryId().equals("") || connection.getProviderByProviderId().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("connections", factoryProviderService.getСonnections());
            return "connection";
        }
        factoryProviderService.insertConnection(connection);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("connections", factoryProviderService.getСonnections());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("providers", providerService.getProviders());
        return "connection";
    }
    @RequestMapping(value = "/deleteconnection", method = RequestMethod.POST)
    public String deleteConnection(@ModelAttribute("connection") FactoryProviderEntity connection, Model model, int connectionId) {

        factoryProviderService.deleteById(connectionId);
        model.addAttribute("connections", factoryProviderService.getСonnections());
        return "redirect:/connection";

    }

    @RequestMapping(value = "/catalogue" , method = RequestMethod.GET)
    public String catalogue(Model model) {
        model.addAttribute("catalogues", catalogueService.getGuns());
        model.addAttribute("catalogue", new CatalogueEntity());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        return "catalogue";
    }

    @RequestMapping(value = "/filtercatalogue", method = RequestMethod.GET)
    public String filterCatalogue(Model model, @RequestParam("price1") int price1, @RequestParam("price2") int price2){
        model.addAttribute("catalogues", catalogueService.filterGuns(price1, price2));
        model.addAttribute("catalogue", new CatalogueEntity());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        return "catalogue";
    }

    @RequestMapping(value = "/asccatalogue", method = RequestMethod.GET)
    public String ascCatalogue(Model model){
        model.addAttribute("catalogues", catalogueService.ascGuns());
        model.addAttribute("catalogue", new CatalogueEntity());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        return "catalogue";
    }

    @RequestMapping(value = "/desccatalogue", method = RequestMethod.GET)
    public String descCatalogue(Model model){
        model.addAttribute("catalogues", catalogueService.descGuns());
        model.addAttribute("catalogue", new CatalogueEntity());
        model.addAttribute("factories", factoryService.getFactories());
        model.addAttribute("factory", new FactoryEntity());
        return "catalogue";
    }

    @RequestMapping(value = "/catalogue" , method = RequestMethod.POST)
    public String createCatalogue(@ModelAttribute("catalogue") CatalogueEntity catalogue, @ModelAttribute("factory") FactoryEntity factory, Model model) {
        if (catalogue.getName().equals("") || catalogue.getPrice().equals("") || catalogue.getFactoryByFactoryId().equals("") || catalogue.getCategory().equals("") || catalogue.getCaliber().equals("") || catalogue.getRange().equals("") || catalogue.getSpeed().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("catalogues", catalogueService.getGuns());
            return "catalogue";
        }
        catalogueService.insertGun(catalogue);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("catalogues", catalogueService.getGuns());
        model.addAttribute("factories", factoryService.getFactories());
        return "catalogue";
    }
    @RequestMapping(value = "/deletecatalogue", method = RequestMethod.POST)
    public String deleteCatalogue(@ModelAttribute("catalogue") CatalogueEntity catalogue, Model model, int catalogueId) {

        catalogueService.deleteById(catalogueId);
        model.addAttribute("catalogues", catalogueService.getGuns());
        return "redirect:/catalogue";

    }

    @RequestMapping(value = "/pricecatalogue", method = RequestMethod.POST)
    public String changePrice(@ModelAttribute("catalogue") CatalogueEntity catalogue, @RequestParam(value = "price") Integer price, Model model) {

        catalogueService.updatePrice(catalogue.getId(), price);
        model.addAttribute("catalogues", catalogueService.getGuns());

        return "redirect:/catalogue";

    }

    @RequestMapping(value = "/storage" , method = RequestMethod.GET)
    public String storage(Model model) {
        model.addAttribute("storages", storageService.getItems());
        model.addAttribute("storage", new StorageEntity());
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("order", new OrderEntity());
        model.addAttribute("catalogues", catalogueService.getGuns());
        model.addAttribute("catalogue", new CatalogueEntity());
        return "storage";
    }

    @RequestMapping(value = "/storage" , method = RequestMethod.POST)
    public String createStorage(@ModelAttribute("storage") StorageEntity storage, @ModelAttribute("order") OrderEntity order, @ModelAttribute("catalogue") CatalogueEntity catalogue, Model model) {
        if (storage.getCatalogueByCatalogueId().equals("") || storage.getOrderByOrderId().equals("")) {
            model.addAttribute("error", "Не все поля заполнены");
            model.addAttribute("storages", storageService.getItems());
            return "storage";
        }
        storageService.insertItem(storage);

        model.addAttribute("success", "Успешно добавлено");
        model.addAttribute("storages", storageService.getItems());
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("catalogues", catalogueService.getGuns());
        return "storage";
    }
    @RequestMapping(value = "/deletestorage", method = RequestMethod.POST)
    public String deleteStorage(@ModelAttribute("storage") StorageEntity storage, Model model, int storageId) {

        storageService.deleteById(storageId);
        model.addAttribute("storages", storageService.getItems());
        return "redirect:/storage";

    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home() {
        return "home";
    }


}
