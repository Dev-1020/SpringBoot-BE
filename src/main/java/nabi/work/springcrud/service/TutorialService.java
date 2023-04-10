package nabi.work.springcrud.service;

import nabi.work.springcrud.model.Tutorial;
import nabi.work.springcrud.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;


    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Tutorial getByTutorialId(Long id) {
        return tutorialRepository.findById(id).orElse(null);
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateByTutorialId(Long id, Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial existingTutorial = tutorialData.get();
            existingTutorial.setTitle(tutorial.getTitle());
            existingTutorial.setDescription(tutorial.getDescription());
            existingTutorial.setPublished(tutorial.getPublished());
            return tutorialRepository.save(existingTutorial);
        } else {
            return null;
        }
    }

    public void deleteTutorialById(Long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findTutorialsByTitle(String title) {
        if (title == null) {
            return  tutorialRepository.findAll();
        } else {
            return tutorialRepository.findTutorialsByTitle(title);
        }
    }
}
