package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.response.member.MemberData;

public interface MemberService {

    public MemberData join(Member member);

    public void checkDuplicatedMember(Member member);

}
