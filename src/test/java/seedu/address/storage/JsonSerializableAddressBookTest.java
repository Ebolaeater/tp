package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalVendors;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_VENDORS_FILE = TEST_DATA_FOLDER.resolve("typicalVendorsAddressBook.json");
    private static final Path STORAGE_FILE = TEST_DATA_FOLDER.resolve("storageAddressBook.json");
    private static final Path INVALID_VENDOR_FILE = TEST_DATA_FOLDER.resolve("invalidVendorAddressBook.json");
    private static final Path DUPLICATE_VENDOR_FILE = TEST_DATA_FOLDER.resolve("duplicateVendorAddressBook.json");

    @Test
    public void toModelType_typicalVendorsFile_success() throws Exception {
        AddressBook book = TypicalVendors.getTypicalAddressBook();
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(STORAGE_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook dataBook = dataFromFile.toModelType();
        assertEquals(dataBook, book);
    }

    @Test
    public void toModelType_invalidVendorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_VENDOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVendors_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_VENDOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
