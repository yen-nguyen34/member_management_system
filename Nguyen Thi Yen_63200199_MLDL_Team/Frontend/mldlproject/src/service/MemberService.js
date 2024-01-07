import axios from 'axios'

const MEMBER_API_URL = 'http://localhost:8081/api/v1'

class MemberDataService {

    retrieveAllMembers() {
        return axios.get(`${MEMBER_API_URL}/members`);
    }

    searchMembers(params) {
        return axios.post(`${MEMBER_API_URL}/members/search`, null, { params });
    }

    downloadMembers(params) {
        return axios.get(`${MEMBER_API_URL}/members/download`, { params, responseType: 'blob' });
    }
    retrieveMember(id) {
        return axios.get(`${MEMBER_API_URL}/members/${id}`);
    }

    signUpMember(member) {
        return axios.post(`${MEMBER_API_URL}/members/sign-up`, member);
    }

    createMember(member) {
        return axios.post(`${MEMBER_API_URL}/members/create`, member);
    }

    updateMember(id, member) {
        return axios.put(`${MEMBER_API_URL}/members/update/${id}`, member);
    }

    deleteMember(memberNo) {
        return axios.delete(`${MEMBER_API_URL}/members/delete/${memberNo}`);
    }

    checkDuplicateId(memberId) {
        return axios.get(`${MEMBER_API_URL}/members/check-duplicate`, { params: { memberId } });
    }

    validatePassword(memberPwd) {
        return axios.get(`${MEMBER_API_URL}/members/validate-password`, { params: { memberPwd } });
    }

}
export default new MemberDataService()
