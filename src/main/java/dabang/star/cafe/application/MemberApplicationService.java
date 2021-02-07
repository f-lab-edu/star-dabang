package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.DuplicatedException;
import dabang.star.cafe.application.data.MemberId;
import dabang.star.cafe.domain.member.EncryptService;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.infrastructure.repository.MybatisMemberRepository;
import dabang.star.cafe.infrastructure.mapper.read.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberApplicationService {

    private final MybatisMemberRepository mybatisMemberRepository;
    private final MemberReadService memberReadService;
    private final EncryptService encryptService;

    @Transactional
    public MemberId join(Member member) {

        checkDuplicatedMember(member);
        member.setPassword(encryptService.encrypt(member.getPassword()));

        Long saveId = mybatisMemberRepository.save(member);

        return new MemberId(saveId);
    }

    private void checkDuplicatedMember(Member member) {

        if (memberReadService.exist(member.getEmail())) {
            throw new DuplicatedException("duplicated Email");
        }
    }
}
