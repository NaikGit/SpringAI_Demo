package net.springio.chatpdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Spring MVC Controller for handling document uploads and displaying the chat interface.
 */
@Controller
public class DocumentController {

	private final DocumentService documentService;

	/**
	 * Constructs a new DocumentController with the specified DocumentService.
	 *
	 * @param documentService The service responsible for document processing.
	 */
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	/**
	 * Handles the file upload from the user.
	 * It receives a MultipartFile, passes its resource to the DocumentService for loading and processing,
	 * and then redirects the user to the chat page.
	 *
	 * @param document The MultipartFile uploaded by the user, bound to the request parameter "fileToUpload".
	 * @return A redirect string to the chat page, which will trigger the loadChat() method.
	 */
	@PostMapping("/upload")
	public String uploadDocument(@RequestParam("fileToUpload") MultipartFile document) {
		// Pass the document's resource to the service layer for processing.
		documentService.loadDocument(document.getResource());

		// Redirect the user to the chat interface after the upload is complete.
		return "redirect:/chat";
	}

	/**
	 * Displays the chat page.
	 * This method handles GET requests to "/chat" and returns the name of the chat view template.
	 *
	 * @return The name of the chat view template ("chat").
	 */
	@GetMapping("/chat")
	public String loadChat() {
		return "chat";
	}
}