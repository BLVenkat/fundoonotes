package com.bridgelabz.fundoonotes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.FundooException;
import com.bridgelabz.fundoonotes.repository.LabelRespository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Service
public class LabelServiceImp implements LabelService {

	@Autowired
	UserRepository userRepsitory;

	@Autowired
	LabelRespository labelRespository;

	@Autowired
	TokenService tokenService;

	@Override
	public Label createLabel(String token, LabelDto labelDto) {
		User user = getUser(tokenService.decodeToken(token));
		Optional<Label> labelIsPresent = user.getLabels().stream()
				.filter(label -> label.getLabelName().equals(labelDto.getLabelName())).findFirst();
		if (labelIsPresent.isPresent())
			throw new FundooException(HttpStatus.CONFLICT.value(),
					ApplicationConfig.getMessageAccessor().getMessage("3"));
		Label label = new Label();
		BeanUtils.copyProperties(labelDto, label);
		Label savedLabel = labelRespository.save(label);
		user.getLabels().add(savedLabel);
		userRepsitory.save(user);
		return savedLabel;
	}

	public User getUser(Long userId) {
		return userRepsitory.findById(userId).orElseThrow(() -> new FundooException(HttpStatus.NOT_FOUND.value(),
				ApplicationConfig.getMessageAccessor().getMessage("1")));

	}

	@Override
	public List<Label> getUserLabels(String token) {

		return getUser(tokenService.decodeToken(token)).getLabels();
	}

	@Override
	public void deleteLabel(String token, Long labelId) {
		User user = getUser(tokenService.decodeToken(token));
		Optional<Label> labelIsPresent = user.getLabels().stream().filter(label -> label.getId().equals(labelId))
				.findFirst();
		if (!labelIsPresent.isPresent())
			throw new FundooException(HttpStatus.NOT_FOUND.value(),
					ApplicationConfig.getMessageAccessor().getMessage("5"));
		labelRespository.deleteById(labelId);
	}

	@Override
	public Label updateLabel(String token, Long labelId, LabelDto labelDto) {
		User user = getUser(tokenService.decodeToken(token));
		Optional<Label> labelIsPresent = user.getLabels().stream().filter(label -> label.getId().equals(labelId))
				.findFirst();
		if (!labelIsPresent.isPresent())
			throw new FundooException(HttpStatus.NOT_FOUND.value(),
					ApplicationConfig.getMessageAccessor().getMessage("5"));
		labelIsPresent.get().setLabelName(labelDto.getLabelName());
		return labelRespository.save(labelIsPresent.get());
	}

}
