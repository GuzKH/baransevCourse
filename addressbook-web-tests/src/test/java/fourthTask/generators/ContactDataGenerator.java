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
            writer.write(String.format("%s;%s;%s;%s;%s\n",
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
            contacts.add(new ContactData().withFirstName(String.format("test %s", i))
                    .withLastName(String.format("test %s", i))
                    .withAddress(String.format("test %s", i))
                    .withEmail(String.format("test@%s", i))
                    .withAllPhones(String.format("981111111%s", i)));
        }
        return contacts;
    }
}
