package fourthTask.generators;

import fourthTask.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContact(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(" . ").getAbsolutePath());

        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n",
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getAddress(),
                    contact.getEmail(),
                    contact.getAllPhones()));
        }
        writer.close();
    }

    private static List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("%s", i))
                    .withLastName(String.format("%s", i))
                    .withAddress(String.format("t %s", i))
                    .withEmail(String.format("t %s", i))
                    .withAllPhones(String.format("t %s", i)));
        }
        return contacts;
    }
}
