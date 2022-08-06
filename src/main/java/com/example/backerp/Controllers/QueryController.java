package com.example.backerp.Controllers;

import com.example.backerp.Models.Associations;
import com.example.backerp.Models.Conventions;
import com.example.backerp.Models.Evenement;
import com.example.backerp.Models.Utilisateur;
import com.example.backerp.Resolvers.QueryResolver;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@Controller

public class QueryController {
    private final QueryResolver queryResolver;
    public QueryController(QueryResolver queryResolver) {
        this.queryResolver = queryResolver;
    }
    @GraphQLQuery(name = "getAllusers")
    @SchemaMapping(value = "getAllusers",typeName = "Query")
    public List<Utilisateur> getAllusers(){
        return queryResolver.getAllusers();
    }
    @GraphQLQuery(name = "getUserByCin")
    @SchemaMapping(value = "getUserByCin",typeName = "Query")
    public Utilisateur getUserByCin(String cin){
        return queryResolver.getUserByCin(cin);
    }
    @GraphQLQuery(name = "getAllEvents")
    @SchemaMapping(value = "getAllEvents",typeName = "Query")
    public List<Evenement> getAllEvents(){
        return queryResolver.getAllEvents();
    }
    @GraphQLQuery(name = "getAllAssociations")
    @SchemaMapping(value = "getAllAssociations",typeName = "Query")
    public List<Associations> getAllAssociations(){
        return queryResolver.getAllAssociations();
    }
    @GraphQLQuery(name = "getAllConventions")
    @SchemaMapping(value = "getAllConventions",typeName = "Query")
    public List<Conventions> getAllConventions(){
        return queryResolver.getAllConventions();
    }
}
