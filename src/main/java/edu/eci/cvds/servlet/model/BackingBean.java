   
package edu.eci.cvds.servlet.model;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;


@ManagedBean(name = "BackingBean")
@SessionScoped 
 
public class BackingBean {
	
	private List<Double> listadoValores = Collections.emptyList();
	private double promedio = 0;
	private double desviacionEstandar = 0;
	private double varianza = 0;
	private double moda = 0;
	
	public BackingBean() {
		restart();
	}
	
    public void restart(){
    	listadoValores.clear();
    	promedio = 0;
    	desviacionEstandar = 0;
    	varianza = 0;
    	moda = 0;
    }
    
    public List<Double> getListadoValores(){
    	return this.listadoValores;
    }
    
    public double getPromedio(){
    	return this.promedio;
    }
     
    public double getDesviacionEstandar(){
    	return this.desviacionEstandar;
    }
    
    public double getVarianza(){
    	return this.varianza;
    }
    
    public double getModa() {  
    	return this.moda;           
    }
    
    public void setListadoValores(String listadoValores) {
    	this.listadoValores = Arrays.asList(listadoValores.split(";")).stream().map( e -> Double.parseDouble(e)).collect(Collectors.toList());
    	
    }
    
    public void setPromedio(double promedio) {
    	this.promedio = promedio;
    }
    
    public void setDesviacionEstandar(double desviacionEstandar) {
    	this.desviacionEstandar = desviacionEstandar;
    }
    
    public void setVarianza(double varianza) {
    	this.varianza = varianza;
    }
    
    public void setModa(double moda) {
    	this.moda = moda;
    }
    
    public void calculateMean() {
    	double suma = 0;
    	for(int i= 0; i < listadoValores.size() ; i++) {
    		suma += listadoValores.get(i);
    	}
    	suma = suma/listadoValores.size();
    	setPromedio(suma);
    }
    
    private double promedioalCuadrado() {
    	double suma = 0;
    	for(int i= 0; i < listadoValores.size() ; i++) {
    		suma += Math.pow(listadoValores.get(i), 2); 
    	}
    	suma = suma/listadoValores.size(); 
    	return suma;
    }
    
    public void calculateStandardDeviation() {
    	calculateVariance();
    	setDesviacionEstandar(Math.sqrt(getVarianza()));
    }
    
    public void calculateVariance() {
    	calculateMean();
    	double miu = Math.pow(getPromedio(), 2); 
    	double promedioDos = promedioalCuadrado();
    	setVarianza(promedioDos - miu);
    }
     
    public void calculateMode() {
        double maxValue = 0, maxCount = 0;

        for (int i = 0; i <listadoValores .size(); ++i) {
            int count = 0;
            for (int j = 0; j < listadoValores .size(); ++j) {
                if (listadoValores.get(j) == listadoValores.get(i)) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue =  listadoValores.get(i);
            }
        }
        
        setModa(maxValue);
    }
    
    public void calculadora(String valores) {
    	setListadoValores(valores);
    	calculateMean();
    	calculateVariance();
    	calculateStandardDeviation();
    	calculateMode();
    }
}
