package fr.diginamic.transformer;

import fr.diginamic.controller.dto.FavoriSansUtilisateurDto;
import fr.diginamic.entites.Favori;
import fr.diginamic.exception.CreationFavoriDtoImpossibleException;
import org.springframework.stereotype.Component;

@Component
public class TransformerFavori {

    public FavoriSansUtilisateurDto FavoriToFavoriDto(Favori favori){


        FavoriSansUtilisateurDto favoriDto = new FavoriSansUtilisateurDto();
        if(favori!=null){
        favoriDto.setCommune(favori.getCommune());
        favoriDto.setWeatherDescription(favori.getWeatherDescription());
        favoriDto.setWeatherIcon(favori.getWeatherIcon());
        favoriDto.setTemperature(favori.getTemperature());
        favoriDto.setPressure(favori.getPressure());
        favoriDto.setHumidity(favori.getHumidity());
        favoriDto.setTempMin(favori.getTempMin());
        favoriDto.setTempMax(favori.getTempMax());
        favoriDto.setWindSpeed(favori.getWindSpeed());
        favoriDto.setWindDegrees(favori.getWindDegrees());

        favoriDto.setMesureSO2(favori.getMesureSO2());
        favoriDto.setMesurePM25(favori.getMesurePM25());
        favoriDto.setMesurePM10(favori.getMesurePM10());
        favoriDto.setMesureO3(favori.getMesureO3());
        favoriDto.setMesureNO2(favori.getMesureNO2());
        favoriDto.setMesureCO(favori.getMesureCO());
        return favoriDto;}
        else{
            throw new CreationFavoriDtoImpossibleException();
        }


    }
}
