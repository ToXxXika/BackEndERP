package com.example.backerp.Resolvers;

import com.example.backerp.Models.Associations;
import com.example.backerp.Models.Conventions;
import com.example.backerp.Models.Evenement;
import com.example.backerp.Models.Utilisateur;
import com.example.backerp.Repositories.AssociationsRepository;
import com.example.backerp.Repositories.ConventionsRepository;
import com.example.backerp.Repositories.EvenementRepository;
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
     @Autowired
     private EvenementRepository ER;
     @Autowired
     private AssociationsRepository AR ;
     @Autowired
     private ConventionsRepository CR ;

     public List<Utilisateur> getAllusers(){
         return (List<Utilisateur>) UR.findAll();
     }
     public Utilisateur getUserByCin(String cin){
          Optional<Utilisateur> utilisateur = UR.findByCin(cin);
          if(utilisateur.isPresent())
               return utilisateur.get();
          throw new GraphQLException("utilisateur non trouvé");

     }
     //get all events from the database and return them as a list of events
        public List<Evenement> getAllEvents(){
            return (List<Evenement>) ER.findAll();
        }
     //get all Associations from the database and return them as a list of Associations
        public List<Associations> getAllAssociations(){
            return (List<Associations>) AR.findAll();
        }
     //get all Conventions from the database and return them as a list of Conventions
        public List<Conventions> getAllConventions(){
            return (List<Conventions>) CR.findAll();
        }

}
