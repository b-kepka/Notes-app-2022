import javax.swing.*;

public class Menu extends JMenuBar {
    JMenu notatkaMenu, szukajMenu;
    JMenuItem nowaNotatka,usunNotatke,zamknij,szukajID,szukajDataDodania,szukajDataWaznosci;
    public Menu(){
        notatkaMenu = new JMenu("Notatki");
        szukajMenu = new JMenu("Wyszukaj");
        nowaNotatka = new JMenuItem("Nowa notatka");
        usunNotatke = new JMenuItem("Usuń notatkę");
        zamknij = new JMenuItem("Zamknij");
        szukajID = new JMenuItem("Wyszukaj po ID");
        szukajDataDodania = new JMenuItem("Wyszukaj po dacie dodania");
        szukajDataWaznosci = new JMenuItem("Wyszukaj po dacie ważności");
        notatkaMenu.add(nowaNotatka);
        notatkaMenu.add(usunNotatke);
        notatkaMenu.add(zamknij);
        szukajMenu.add(szukajID);
        szukajMenu.add(szukajDataDodania);
        szukajMenu.add(szukajDataWaznosci);
        this.add(notatkaMenu);
        this.add(szukajMenu);
    }
}
