<template>
    <div class="flex items-center justify-center min-h-screen bg-gray-100">
        <div class="px-8 py-6 mx-4 mt-4 text-left bg-white shadow-lg md:w-1/3 lg:w-1/3 sm:w-1/3">
            <div class="flex justify-center">
                <svg class="w-16 h-16 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                    fill="none" viewBox="0 0 20 21">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M10 3.464V1.1m0 2.365a5.338 5.338 0 0 1 5.133 5.368v1.8c0 2.386 1.867 2.982 1.867 4.175C17 15.4 17 16 16.462 16H3.538C3 16 3 15.4 3 14.807c0-1.193 1.867-1.789 1.867-4.175v-1.8A5.338 5.338 0 0 1 10 3.464ZM1.866 8.832a8.458 8.458 0 0 1 2.252-5.714m14.016 5.714a8.458 8.458 0 0 0-2.252-5.714M6.54 16a3.48 3.48 0 0 0 6.92 0H6.54Z" />
                </svg>
            </div>
            <br>
            <h3 class="text-2xl font-bold text-center">Log In</h3>
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
                    <div class="flex">
                        <button class="w-full px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900">Log In</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>
  
<script>
import MemberService from "@/service/MemberService";

export default {
    name: "LogIn",

    data() {
        return {
            member: {
                memberId: "",
                memberPwd: "",
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
