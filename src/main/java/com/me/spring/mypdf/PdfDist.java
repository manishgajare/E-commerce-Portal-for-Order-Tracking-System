package com.me.spring.mypdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import org.hibernate.id.enhanced.TableStructure;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfGraphics2D;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.TextField;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.me.spring.pojo.Inventory;

public class PdfDist extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List inventoryItemList = (List) model.get("inventoryItemList");
		
		Table table = new Table(5);
		table.setBorder(1);
		table.setPadding(2);
		table.setCellsFitPage(true);
		
		table.addCell("Name");
		table.addCell("Product Code");
		table.addCell("Category");
		table.addCell("Quantity");
		table.addCell("Cost");
		
		
		for(int i=0;i<inventoryItemList.size();i++){
			
			Inventory inventory = (Inventory)inventoryItemList.get(i);
			table.addCell(inventory.getName());
			table.addCell(inventory.getProductCode());
			table.addCell(inventory.getCategory());
			table.addCell(String.valueOf(inventory.getQuantityAvailable()));
			table.addCell(String.valueOf(inventory.getCost()));
			
		}
		
		
		
		
		document.addAuthor("Manish Gajare");
		document.addSubject("These are the products on website");
	
		document.add(table);
		
		
	}


}
