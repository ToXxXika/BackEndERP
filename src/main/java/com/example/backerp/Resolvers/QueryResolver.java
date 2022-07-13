package com.example.backerp.Resolvers;

import com.example.backerp.Models.Utilisateur;
import com.example.backerp.Repositories.UtilisateurRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver  implements GraphQLQueryResolver {

     @Autowired
     private UtilisateurRepository UR ;

     public List<Utilisateur> getAllusers(){
         return (List<Utilisateur>) UR.findAll();
     }
     public Utilisateur getUserByCin(String cin){
          Optional<Utilisateur> utilisateur = UR.findByCin(cin);
          if(utilisateur.isPresent())
               return utilisateur.get();
          throw new GraphQLException("utilisateur non trouvé");

     }
}
