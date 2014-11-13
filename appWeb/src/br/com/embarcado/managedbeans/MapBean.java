package br.com.embarcado.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@ManagedBean
@ViewScoped
public class MapBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private MapModel advancedModel;

	private Marker marker;

//	public MapBean() {
	@PostConstruct
	public void init(){
		advancedModel = new DefaultMapModel();
		
		//Shared coordinates
		LatLng coord1 = new LatLng(-3.136906077389079, -59.98260598629713);
		LatLng coord2 = new LatLng(-3.1376184757053394, -59.97981112450361);
		/*LatLng coord3 = new LatLng(36.879703, 30.706707);
		LatLng coord4 = new LatLng(36.885233, 30.702323);*/
		
		//Icons and Data
		
		advancedModel.addOverlay(new Marker(coord1, "FUCAPI", "", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		advancedModel.addOverlay(new Marker(coord2, "FPF TECH", "", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
		/*advancedModel.addOverlay(new Marker(coord4, "Kaleici", "kaleici.png", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
		advancedModel.addOverlay(new Marker(coord3, "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));*/
		
		for(Marker premarker : advancedModel.getMarkers()){
			premarker.setDraggable(true);
		}
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
//				.getMarker();
		
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
	}
	
	 public void onMarkerDrag(MarkerDragEvent event) {
	        marker = event.getMarker();
	          
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
	    }
	
	public Marker getMarker() {
		return marker;
	}
	
	 public void pontoSelecionado(PointSelectEvent event) {  
	        LatLng latituteLongitude = event.getLatLng();  
	           System.out.println("Lat:" + latituteLongitude.getLat() + ", Long:" + latituteLongitude.getLng());
	        FacesContext.getCurrentInstance().addMessage(
	            null,
	            new FacesMessage(
	                FacesMessage.SEVERITY_INFO, 
	                "Ponto selecionado", 
	                "Lat:" + latituteLongitude.getLat() + ", Long:" + latituteLongitude.getLng()
	            )
	        );  
	    }  
}
