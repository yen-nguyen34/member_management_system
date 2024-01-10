<template>
    <div class="flex items-center justify-center min-h-screen bg-gray-100">
        <div class="px-8 py-6 mx-4 mt-4 text-left bg-white shadow-lg md:w-1/3 lg:w-1/3 sm:w-1/3">
            <div class="flex justify-center">
                <svg class="w-16 h-16 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                    fill="none" viewBox="0 0 20 18">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M13 8h6m-3 3V5m-6-.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0ZM5 11h3a4 4 0 0 1 4 4v2H1v-2a4 4 0 0 1 4-4Z" />
                </svg>
            </div>
            <br>
            <h3 class="text-2xl font-bold text-center">Add Member Account</h3>
            <form @submit.prevent="save">

                <!-- ERROR MESSAGE -->
                <div class="mt-2 text-sm font-bold text-red-600 dark:text-red-500" v-bind:key="index"
                    v-for="(error, index) in errors">
                    {{ error }}
                </div>

                <div class="mt-4">
                    <div>
                        <label class="block" for="ID">ID</label>
                        <input type="text" placeholder="ID"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberId" required>
                    </div>
                    <div class="mt-4">
                        <label class="block" for="password">Password</label>
                        <input type="password" placeholder="Password"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberPwd" required>
                    </div>
                    <p class="mt-2 text-sm text-gray-600">
                        * Only letters, numbers and special characters are entered.
                        <span><br /></span>* 10-20 characters for 2 combinations, 8-20
                        characters for 3 combinations. <span><br /></span>* Special characters
                        are: !@#$%^&* <span><br /></span>* No more than 3 consecutive digits
                        like: 123, 012,...
                    </p>
                    <div class="mt-4">
                        <label class="block">Confirm Password</label>
                        <input type="password" placeholder="Password"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberConfirmPwd" required>
                    </div>
                    <!-- <span class="text-xs text-red-400">Password must be same!</span> -->
                    <div class="mt-4">
                        <label class="block" for="name">Name</label>
                        <input type="text" placeholder="Name"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberName" required>
                    </div>
                    <div class="mt-4">
                        <label class="block" for="phone">Phone</label>
                        <input type="text" placeholder="Phone"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberPhone" required>
                    </div>
                    <div class="mt-4">
                        <label class="block" for="email">Email</label>
                        <input type="text" placeholder="Email"
                            class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                            v-model="member.memberEmail" required>
                    </div>
                    <div>
                    <select v-model="member.selectedDomain" class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600"
                        required>
                        <option selected value="" class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                            Choose a domain
                        </option>
                        <option value="@naver.com">@naver.com</option>
                        <option value="@daum.net">@daum.net</option>
                        <option value="@gmail.com">@gmail.com</option>
                        <option value="@nate.com">@nate.com</option>
                        <option value="@Hotmail.com">@Hotmail.com</option>
                    </select>
                    </div>

                    <div class="mb-4">
                        <p class="text-sm text-gray-600 dark:text-gray-400">
                            Full Email: {{ member.memberEmail + member.selectedDomain }}
                        </p>
                    </div>
                    <div class="flex">
                        <button class="w-full px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900">Create
                            Account</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>
  
<script>
import MemberService from "@/service/MemberService";

export default {
    name: "AddMember",

    data() {
        return {
            member: {
                memberId: "",
                memberPwd: "",
                memberConfirmPwd: "",
                memberName: "",
                memberPhone: "",
                memberEmail: "",
                selectedDomain: "",
            },
            errors: [],
        };
    },

    computed: {
        fullEmail() {
            return this.member.memberEmail + this.member.selectedDomain;
        },
    },

    methods: {
        save() {
            // Clear errors array before validation
            this.errors = [];

            // validate ID
            const memberValidID = this.member.memberId;

            if (!memberValidID.match(/^\d+$/)) {
                this.errors.push("Invalid ID, number required!");
            }

            if (memberValidID.length < 4) {
                this.errors.push("ID must be at least 4 digits long!");
            }

            MemberService.checkDuplicateId(memberValidID)
                .then((response) => {
                    if (response.data === true) {
                        this.errors.push("Duplicate ID, try another ID!");
                    } else {
                        // Validate password
                        const validPwd = this.member.memberPwd;
                        MemberService.validatePassword(validPwd)
                            .then((response) => {
                                if (response.data !== true) {
                                    this.errors.push("Your password is not formatted correctly!");
                                } else {
                                    if (validPwd !== this.member.memberConfirmPwd) {
                                        this.errors.push("Your password is not matched correctly!");
                                    }

                                    // validate name, only letters required
                                    if (!this.member.memberName.match(/^[A-Za-z ]+$/)) {
                                        this.errors.push("Invalid name, letters required!");
                                    }

                                    // validate phone, number required
                                    if (!this.member.memberPhone.match(/^\d+$/)) {
                                        this.errors.push("Invalid phone, numbers required!");
                                    }

                                    // call service to save the member data
                                    if (this.errors.length === 0) {
                                        MemberService.addMember({
                                            memberId: this.member.memberId,
                                            memberPwd: this.member.memberPwd,
                                            memberConfirmPwd: this.member.memberConfirmPwd,
                                            memberName: this.member.memberName,
                                            memberPhone: this.member.memberPhone,
                                            memberEmailName: this.member.memberEmail,
                                            memberSelectedDomain: this.member.memberSelectedDomain,
                                            memberEmail: this.fullEmail,
                                        }).then(() => {
                                            // redirect url "/members" to view all members
                                            this.$router.push("/members");
                                        });
                                    }
                                }
                            })
                            .catch((error) => {
                                console.error("Error checking validate password:", error);
                            });
                    }
                })
                .catch((error) => {
                    console.error("Error checking duplicate ID:", error);
                });
        },
    },
};
</script>
