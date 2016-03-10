package ticket;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.Overlay;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import java.util.Date;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TicketV {
    public static String Serial, Name, Batch, Txn;
    
    public static void Disp()
    {
        try{
            Barcode barcode = BarcodeFactory.createCode128B(Serial);
            barcode.setBarHeight(60);
            barcode.setBarWidth(1);
            File imgFile = new File("testsize.png");
            BarcodeImageHandler.savePNG(barcode, imgFile);
        }
        catch(Exception e){}
        BufferedImage bufferedImage;	
	try {
	  bufferedImage = ImageIO.read(new File("testsize.png"));
	  BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	  newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	  ImageIO.write(newBufferedImage, "jpg", new File("testsize.jpg"));		
	} catch (IOException e) {
	  e.printStackTrace();
	}
        processTicket(Name, Batch, Txn);
    }
    
    private static void processTicket(String name, String Batch, String txn)
    {
        return;
    }
}
