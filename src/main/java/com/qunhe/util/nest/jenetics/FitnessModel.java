package com.qunhe.util.nest.jenetics;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import org.apache.batik.ext.awt.geom.Polygon2D;
import com.qunhe.util.nest.data.NestPath;
import com.qunhe.util.nest.data.Segment;
import com.qunhe.util.nest.util.CommonUtil;
import com.qunhe.util.nest.util.GeometryUtil;

import io.jenetics.DoubleGene;
import io.jenetics.Genotype;

public class FitnessModel {
	
	public List<NestPath> list;
	Double binWidth,binHeight;
	
	/**
	 * @param l		List of polygons
	 * @param binW	Width of bin polygon
	 * @param binH	Height of bin polygon
	 */
	public FitnessModel(List<NestPath> l, double binW, double binH) {
		
		this.list = l;
		this.binHeight=binH;
		this.binWidth=binW;
	}
	
	
	
	
	/**
	 * @param model
	 * @return double value of the fitness, in this case the area that contains all the polygons divided by the sum of the areas of all the polygons
	 */
	public double scalarFitness(final Genotype<DoubleGene> model)
    {		
		//double maxX=0; double maxY=0;	double totArea=0;
		double penalty=0;
		ArrayList<NestPath> polys = CommonUtil.cloneArrayListNestpath(list);
		ModelFactory.convert(model, polys);
		
		for(int i=0; i<polys.size();i++)
		{
			NestPath p = polys.get(i);
			//totArea+=Math.abs(GeometryUtil.polygonArea(p));
//			if(p.getMaxX()>binWidth) penalty+=p.getMaxX()-binWidth;
//			if(p.getMaxY()>binHeight) penalty+=p.getMaxY()-binHeight;
//			if(p.getMinX()<0) penalty+=-p.getMinX()*Math.abs(p.area);
//			if(p.getMinY()<0) penalty+=-p.getMinY()*Math.abs(p.area);
//
//			penalty+= (p.getMaxX()*4+ p.getMaxY()*2);
			
			penalty+=p.getMinX()+p.getMinY();
			
//			List<Segment> ls = polys.get(i).getSegments();
//			
//			for(int j=0; j<ls.size();j++)
//			{
//				if (ls.get(j).getX()>maxX) maxX = ls.get(j).getX();
//				if (ls.get(j).getY()>maxY) maxY = ls.get(j).getY();
//			}			
		}		
		
//		penalty += maxX*list.size()*2;
//		penalty += maxY*list.size();
		//penalty+= maxX*maxY;
		
        //double area = rectBounds.getWidth() * 2 + rectBounds.getHeight();		
       // ArrayList<NestPath> new_list = (ArrayList<NestPath>) Model_Factory.convert(model,list);
        
		//penalty = (maxX*maxY)/totArea;
//        for (int i=0; i< polys.size(); i++){
//            for (int j=0; j< polys.size(); j++){
//                if(i!=j) 
//                {
//                	double add= overlapDouble(polys.get(i).toPolygon2D(), polys.get(j).toPolygon2D());
//                    penalty += add;
//                    if(add>0) penalty+=list.size()*100;
//                    
////                	NestPath p1 = polys.get(i);
////                	NestPath p2 = polys.get(j);
////
////                	if(GeometryUtil.intersect(p1, p2)) penalty+=50;
//                }
//                
//            }
//        }

        return penalty;
    }

}
