package org.formation.controller.mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.model.Role;
import org.formation.repositories.MemberRepository;
import org.formation.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "admin")
public class FormMemberController {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping(value = "form-members")
	public ModelMap mmFormElements(Model model) {
		model.addAttribute("member", new Member());

//		model.addAttribute("roles", roleRepository.findAll());

		return new ModelMap();
	}

	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	@ModelAttribute("members")
	public List<Member> getMembers() {
		return memberRepository.findAll();
	}

	@RequestMapping(value = "find-members")
	public String findMembers(Model model) {

		model.addAttribute("members", memberRepository.findAll());
		return "members";
	}

	@GetMapping("/edit-member")
	public String editMember(@RequestParam("memberId") long memberId, Model model) {
		model.addAttribute("member",
				memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException()));
		return "/admin/form-edit-member";
	}

	@PostMapping(path = "/save-member", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@Valid Member member, Model model, BindingResult result) {

		Member existingMember = memberRepository.findByLogin(member.getLogin()).orElseThrow(() -> new EntityNotFoundException());

		if (existingMember != null && existingMember.getLogin() != null
				&& !existingMember.getLogin().isEmpty()) {

			existingMember.setLogin(member.getLogin());
			existingMember.setFirstName(member.getFirstName());
			existingMember.setLastName(member.getLastName());
			existingMember.setEmail(member.getEmail());
			existingMember.setTelephone(member.getTelephone());
			existingMember.setDisabled(member.getDisabled());
			existingMember.setCreated(member.getCreated());
			existingMember.setRoles(member.getRoles());

			member = memberRepository.save(existingMember);

			result.rejectValue("login", null,
					"There is already a member saved with the same login. Update Done");

		}

		if (result.hasErrors()) {
			model.addAttribute("member", member);
			return "/admin/form-members";
		}
		member = memberRepository.save(member);

		model.addAttribute("members", memberRepository.findAll());
		return "redirect:/admin/form-members?success";

	}

	@GetMapping("/delete-member")
	public String deleteMember(@RequestParam("memberId") long memberId, Model model) {
		Member catToDelete = memberRepository.findById(memberId).get();
		memberRepository.delete(catToDelete);
		model.addAttribute("member", new Member());
		model.addAttribute("members", memberRepository.findAll());

		return "/admin/form-members";
	}

}
