package pages;

import org.openqa.selenium.By;

public interface ProfileEditorPage {
    By lastNameField = By.cssSelector("[name=\"lastName\"]");
    By firstNameField = By.cssSelector("[name=\"firstName\"]");
    By middleNameField = By.cssSelector("[name=\"middleName\"]");
    By phoneField = By.cssSelector("[name=\"phone\"]");
    By qualificationSelect = By.cssSelector("div.Editor_inputContainerFull__KD6wr > select ");
    By profilePic = By.cssSelector("div.Editor_photo__1j-Us");
    By saveButton = By.cssSelector("button.Editor_bottomPanelButtonSave__1jxcT");
    By cancelButton = By.cssSelector("button.Editor_bottomPanelButtonCancel__1tMhy");
    By disabledButton = By.className("Editor_disabled__9vAID");
    By picPickButton = By.xpath(".//button[text()=\"Выберите фото\"]");
    By picUploadInput = By.cssSelector("input[type=\"file\"]");
    By picSaveButton = By.xpath(".//button[@class=\"PhotoEditor_actionButton__2SxIj\"][text()=\"Сохранить\"]");
    By picCancelButton = By.xpath(".//button[@class=\"PhotoEditor_actionButton__2SxIj\"][text()=\"Отмена\"]");
    By picSliderZoomButton = By.cssSelector(".PhotoEditor_bottomPartContentContainer__2PRYv :first-child span [aria-valuenow]");
    By picSliderRotateButton = By.cssSelector(".PhotoEditor_bottomPartContentContainer__2PRYv :nth-child(2) span [aria-valuenow]");
    By picZoomSliderValue = By.cssSelector(".PhotoEditor_bottomPartContentContainer__2PRYv :first-child input");
    By deleteButton = By.className("Editor_removeContainer__rnyCv");
    By verifyDeleteButton = By.xpath(".//button[text()=\"Удалить сотрудника\"]");


    default void inputLastName(String lastName) {
    }

    default void inputFirstName(String firstName) {
    }

    default void inputMiddleName(String middleName) {
    }

    default void inputPhone() {

    }

    default void setProfilePic(String picLink) {
    }
}
