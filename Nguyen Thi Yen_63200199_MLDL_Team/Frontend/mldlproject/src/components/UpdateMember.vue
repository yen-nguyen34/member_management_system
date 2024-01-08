<template>
  <div class="bg-grey-lighter min-h-screen flex flex-col">
    <br />
    <div
      class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center"
    >
      <form
        class="bg-gray-300 px-6 py-8 rounded shadow-md text-black w-full"
        @submit.prevent="save"
      >
        <h2
          class="mb-8 text-3xl text-center font-bold text-gray-900 dark:text-white"
        >
          Update Information Member
        </h2>
        <!-- unique key to each error message -->
        <div
          class="mt-2 text-sm font-bold text-red-600 dark:text-red-500"
          v-bind:key="index"
          v-for="(error, index) in errors"
        >
          {{ error }}
        </div>

        <label
          for="input-group-1"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >ID</label
        >
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="memberId"
          placeholder=""
          v-model="member.memberId"
          @blur="checkDuplicateId"
          required
        />

        <label
          for="input-group-2"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Change password</label
        >
        <input
          name="memberPwd"
          type="text"
          placeholder=""
          class="block border border-grey-light w-full p-3 rounded mb-4"
          v-model="member.memberPwd"
          required
        />
        <p class="mt-2 text-sm text-gray-600">
          * Only letters, numbers and special characters are entered.
          <span><br /></span>* 10-20 characters for 2 combinations, 8-20
          characters for 3 combinations. <span><br /></span>* Special characters
          are: !@#$%^&* <span><br /></span>* No more than 3 consecutive digits
          like: 123, 012,...
        </p>
        <br />
        <label
          for="input-group-3"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Confirm password</label
        >
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="memberConfirmPwd"
          placeholder=""
          v-model="member.memberConfirmPwd"
          required
        />

        <label
          for="input-group-4"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Name</label
        >
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="memeberName"
          placeholder=""
          v-model="member.memberName"
          required
        />

        <label
          for="input-group-5"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Mobile Phone</label
        >
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="memberPhone"
          placeholder=""
          v-model="member.memberPhone"
          required
        />

        <label
          for="input-group-6"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Email Name</label
        >
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="memeberEmail"
          placeholder=""
          v-model="member.memberEmail"
          required
        />

        <select
          v-model="member.selectedDomain"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          required
        >
          <option
            selected
            value=""
            class="block font-bold border-grey-light w-full p-3 rounded mb-4"
          >
            Choose a domain
          </option>
          <option value="@naver.com">@naver.com</option>
          <option value="@daum.net">@daum.net</option>
          <option value="@gmail.com">@gmail.com</option>
          <option value="@nate.com">@nate.com</option>
          <option value="@Hotmail.com">@Hotmail.com</option>
        </select>

        <div class="mb-4">
          <p class="text-sm text-gray-600 dark:text-gray-400">
            Full Email: {{ member.memberEmail + member.selectedDomain }}
          </p>
        </div>

        <button
          type="submit"
          class="text-white bg-blue-500 hover:bg-blue-500 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-base inline-flex items-center px-3 py-2.5 text-center mr-2"
        >
          Update Account
        </button>

        <button
          @click="closeUpdateForm"
          type="button"
          class="text-gray-900 bg-white hover:bg-gray-100 focus:ring-4 focus:ring-cyan-200 border border-gray-200 font-medium inline-flex items-center rounded-lg text-base px-3 py-2.5 text-center"
        >
          No, cancel
        </button>
      </form>
    </div>
  </div>
</template>
  
  <script>
import MemberService from "@/service/MemberService";

export default {
  name: "UpdateMember",

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
      confirmUpdateForm: false,
    };
  },

  computed: {
    fullEmail() {
      return this.member.memberEmail + this.member.selectedDomain;
    },

    memberNo() {
      return this.$route.params.memberNo;
    },
  },

  methods: {
    refreshMemberDetails() {
      MemberService.retrieveMember(this.memberNo).then((response) => {
        this.member.memberId = response.data.memberId;
        this.member.memberPwd = response.data.memberPwd;
        this.member.memberConfirmPwd = response.data.memberConfirmPwd;
        this.member.memberName = response.data.memberName;
        this.member.memberPhone = response.data.memberPhone;
        this.member.memberEmail = response.data.memberEmail;
        this.member.memberEmail = response.data.memberEmail;
      });
    },

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
                    MemberService.updateMember(this.memberNo, {
                      memberId: this.member.memberId,
                      memberPwd: this.member.memberPwd,
                      memberConfirmPwd: this.member.memberConfirmPwd,
                      memberName: this.member.memberName,
                      memberPhone: this.member.memberPhone,
                      memberEmail: this.fullEmail,
                    }).then(() => {
                      // redirect url "/members" to view all members
                      this.$router.push("/members");
                    //   this.closeUpdateForm();
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

    closeUpdateForm() {
        this.confirmUpdateForm = false;
    },
  },

  created() {
    this.refreshMemberDetails();
  },
};
</script>

<style scoped>
.blur-background {
  backdrop-filter: blur(8px);
  -webkit-backdrop-filter: blur(8px);
}
</style>