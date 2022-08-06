package com.example.backerp.Resolvers;

import com.example.backerp.Models.*;
import com.example.backerp.Repositories.*;
import graphql.GraphqlErrorException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private LogParticipationRepository LPR ;
    @Autowired
    private UtilisateurRepository UR ;
    @Autowired
    private EvenementRepository ER ;
    @Autowired
    private PaieRepository PR ;
    // import  all Repositories in "Repositories" package
    @Autowired
    private DetailevenementRepository DER;
    public Boolean addUser(Utilisateur U){
        try{
            UR.save(U);
            return true ;
        }catch (GraphqlErrorException GEE){
            System.out.println(GEE.getMessage());
            return false;
        }
    }
    public Boolean participate(Logparticipation LP,Integer NbrPlaces){
        boolean Res = false ;
        Optional<Evenement> evenement = ER.findById(LP.getIdeventfk());
        if(evenement.isPresent()){
            Optional<Detailevenement> detailevenement = DER.findById(evenement.get().getDetails());
            if(detailevenement.isPresent()){
                if(detailevenement.get().getPlaces()>NbrPlaces){
                    try {
                        LPR.save(LP);
                        int montant= LP.getMontantpayé();
                        int idevent = LP.getIdeventfk();
                        Iterable<Paie> P = PR.findByRefconv(idevent);
                        if(P.iterator().hasNext()){
                            Paie paie = P.iterator().next();
                            paie.setMontanttotal(paie.getMontanttotal()+montant);
                            paie.setMontantpayeparagile(paie.getMontantpayeparagile()+((detailevenement.get().getPrix()) * (detailevenement.get().getPromotion()))/100);
                            if(updateplaces(detailevenement.get(),NbrPlaces)){
                                PR.save(paie);
                                Res=  true;
                            }
                        }else{
                            Paie paie = new Paie();
                            paie.setMontanttotal(montant);
                            paie.setRefconv(P.iterator().next().getRefconv());
                            paie.setMontantpayeparagile((detailevenement.get().getPrix()*detailevenement.get().getPromotion())/100);
                            PR.save(paie);
                            Res=  true;
                        }
                    }catch (GraphqlErrorException GEE){
                        System.out.println(GEE.getMessage());
                    }
                }
            }
        }
        return Res ;
    }
  public boolean updateplaces(Detailevenement DE,int NbrPlaces){
        boolean Res = false ;
        try {
            DE.setPlaces(DE.getPlaces()-NbrPlaces);
            DER.save(DE);
            Res=  true;
        }catch (GraphqlErrorException GEE){
            System.out.println(GEE.getMessage());
        }
        return Res ;
  }
    public Boolean addEvent(Evenement E,Detailevenement ED){
          try{
             DER.save(ED);
             System.out.println("IDDETAIL"+ ED.getIdetail());
             E.setDetails(ED.getIdetail());
             ER.save(E);
            return true ;
          } catch (GraphqlErrorException GEE){
              System.out.println(GEE.getMessage());
              return false;
          }
    }
    public Boolean deleteEvent(Integer idDetail,String codeevenment){
      Optional<Evenement>  Event= ER.deleteEvenementByCodevenement(codeevenment);
       if(Event.isPresent()){
              ER.delete(Event.get());
              DER.deleteById(idDetail);
              return true ;
       }else{
           return false ;
       }
    }

}
