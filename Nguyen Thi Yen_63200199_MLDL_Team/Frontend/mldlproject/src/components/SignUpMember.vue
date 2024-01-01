<template>
    <div class="bg-grey-lighter min-h-screen flex flex-col">
        <br>
        <div class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center">
            <form class="bg-gray-300 px-6 py-8 rounded shadow-md text-black w-full" @submit.prevent="save">
                <h2 class="mb-8 text-3xl text-center font-bold text-gray-900 dark:text-white">Sign Up Member</h2>
                <!-- unique key to each error message -->
                <div class="mt-2 text-sm text-red-600 dark:text-red-500" v-bind:key="index"
                    v-for="(error, index) in errors">
                    {{ error }}
                </div>

                <label for="input-group-1" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">ID</label>
                <input type="text" class="block border border-grey-light w-full p-3 rounded mb-4" name="memberId"
                    placeholder="" v-model="member.memberId" required />

                <label for="input-group-2"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                <input type="password" class="block border border-grey-light w-full p-3 rounded mb-4" name="memberPwd"
                    placeholder="" v-model="member.memberPwd" required />

                <p class="mt-2 text-sm text-gray-600">* Only letters, numbers and special characters are entered.
                    <span><br></span>* 10-20 characters for 2 combinations, 8-20 characters for 3 combinations.
                    <span><br></span>* Special characters are: !@#$%^&*
                    <span><br></span>* No more than 3 consecutive digits like: 123, 012, 890,
                </p>
                <br>
                <label for="input-group-3" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm
                    password</label>
                <input type="password" class="block border border-grey-light w-full p-3 rounded mb-4"
                    name="memberConfirmPwd" placeholder="" v-model="member.memberConfirmPwd" required />

                <label for="input-group-4" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                <input type="text" class="block border border-grey-light w-full p-3 rounded mb-4" name="memeberName"
                    placeholder="" v-model="member.memberName" required />

                <label for="input-group-5" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mobile
                    Phone</label>
                <input type="text" class="block border border-grey-light w-full p-3 rounded mb-4" name="memberPhone"
                    placeholder="" v-model="member.memberPhone" required />

                <label for="input-group-6" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email
                    Name</label>
                <input type="text" class="block border border-grey-light w-full p-3 rounded mb-4" name="memeberEmail"
                    placeholder="" v-model="member.memberEmail" required />

                <select v-model="member.selectedDomain" class="block border border-grey-light w-full p-3 rounded mb-4"
                    required>
                    <option selected value="" class="block font-bold border-grey-light w-full p-3 rounded mb-4">Choose a
                        domain</option>
                    <option value="@naver.com">@naver.com</option>
                    <option value="@daum.net">@daum.net</option>
                    <option value="@gmail.com">@gmail.com</option>
                    <option value="@nate.com">@nate.com</option>
                    <option value="@Hotmail.com">@Hotmail.com</option>
                </select>

                <div class="mb-4">
                    <p class="text-sm text-gray-600 dark:text-gray-400">Full Email: {{ member.memberEmail +
                        member.selectedDomain }}</p>
                </div>

                <button type="submit"
                    class="w-full text-center font-bold py-3 rounded bg-blue-500 text-white hover:bg-green-dark focus:outline-none my-1">Create
                    Account</button>
            </form>
        </div>
    </div>
</template>

<script>
import MemberService from '@/service/MemberService';

export default {
    name: 'SignUpMember',

    data() {
        return {
            memberIDs: [], // Keep track of existing member IDs
            member: {
                memberId: '',
                memberPwd: '',
                memberConfirmPwd: '',
                memberName: '',
                memberPhone: '',
                memberEmail: '',
                selectedDomain: '',
            },
            errors: [],
        };
    },

    computed: {
        fullEmail() {
            return this.member.memberEmail + this.member.selectedDomain;
        }
    },

    methods: {
        save() {

            // Clear errors array before validation
            this.errors = [];

            // validate ID
            const memberValidID = this.member.memberId;
            console.log('Member ID:', memberValidID);

            if (!memberValidID.match(/^\d+$/)) {
                this.errors.push("Invalid ID, number required!");
            }

            if (memberValidID.length < 4) {
                this.errors.push("ID must be at least 4 digits long!");
            }

            if (this.memberIDs.includes(memberValidID)) {
                this.errors.push("Duplicate ID, try another ID!");
            }

            // validate password
            const password = this.member.memberPwd;

            const regex1 = /[a-zA-Z]/; // At least one letter
            const regex2 = /\d/; // At least one digit
            const regex3 = /[!@#$%^&*]/; // At least one special character
            const regexLength12 = /^.{10,20}$/; // 10-20 characters in total
            const regexLength123 = /^.{8,20}$/; // 8-20 characters in total
            const regex5 = /^(?:(?!(\d)\1{2,}).)*$/; // No more than 3 consecutive digits

            if (!((regex1.test(password) && regex2.test(password) && !regex3.test(password)) ||
                (regex1.test(password) && !regex2.test(password) && regex3.test(password)) ||
                (!regex1.test(password) && regex2.test(password) && regex3.test(password)))) {
                this.errors.push("Your password is not formatted correctly!");
            }

            if (!((regex1.test(password) && regex2.test(password) && regex3.test(password) && regexLength123.test(password)))) {
                this.errors.push("Password must be 8-20 characters long!");
            } 

            if (!((regex1.test(password) && regex2.test(password) && !regex3.test(password) && regexLength12.test(password)) ||
                (regex1.test(password) && !regex2.test(password) && regex3.test(password) && regexLength12.test(password)) ||
                (!regex1.test(password) && regex2.test(password) && regex3.test(password) && regexLength12.test(password)))) {
                this.errors.push("Password must be 10-20 characters long!");
            }

            if (!regex5.test(password)) {
                this.errors.push("No more than 3 consecutive digits allowed!");
            }

            if (password != this.member.memberConfirmPwd) {
                this.errors.push("Your password is not matched correctly!");
            }


            // validate name, only letters required
            if (!this.member.memberName.match(/^[A-Za-z ]+$/)) {
                this.errors.push("Invalid name, letters required!");
            }

            // validate phone, number required
            if (!this.member.memberPhone.match(/^\d+$/)) {
                this.errors.push("Invalid phone, number required!");
            }

            // call service to save the member data
            if (this.errors.length === 0) {
                this.memberIDs.push(memberValidID);
                MemberService.signUpMember({
                    memberId: this.member.memberId,
                    memberPwd: this.member.memberPwd,
                    memberConfirmPwd: this.member.memberConfirmPwd,
                    memberName: this.member.memberName,
                    memberPhone: this.member.memberPhone,
                    memberEmail: this.fullEmail,
                }).then(() => {
                    // redirect url "/members" to view all members
                    this.$router.push("/members");
                })
            }

        },
    },
}
</script>