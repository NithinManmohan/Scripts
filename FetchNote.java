public class ListNotesAction {

	// Fetch a note for a given commit ID
	public String FetchNote(String repository, String commitID)
			throws IOException, GitAPIException {

		String repo = repository.replace("\\", "/");
		repo = repo.concat("/.git");
		FileRepository frep = new FileRepository(repo);



		List<Note> call = new Git(frep).notesList().call();

		for (Note note : call) {

			if (note.getName().equals(commitID)) {
				ObjectLoader loader = frep.open(note.getData()); // loader.copyTo(System.out);
				String noteMessage = new String(loader.getBytes(), "UTF-8")
						.trim();

				return noteMessage;
			} else {
				continue;
			}

		}

		return "";
	}
}
