# 📱 PieceJob App - Back end
Java Springboot backend for the PieceJobApplication
## LIVE-Ready for requests: https://piece-job-back-end.onrender.com/

> **Tagline:** *Where skills meet opportunity. Turn your skills into income.*

PieceJob is a modern gig platform that connects young people and skilled individuals with **flexible, short-term work opportunities** in their local area.  
Whether you’re looking to earn extra income or gain real-world experience, PieceJob makes it easy to find jobs that match your skills — **fast, reliable, and on your terms**.  

By leveraging **FNB’s digital payment solutions**, workers are paid instantly and can save or spend responsibly, bridging the gap between informal gig work and financial empowerment.

---

## 📝 Name & Description
- **App Name:** PieceJob  
- **Description:** A mobile-first job platform enabling gig workers and job posters to connect easily and transact securely.  

---

## 🎯 Target Audience & Personas
**Primary Users**
- Youth & job seekers looking for flexible, short-term gigs  
- Skilled individuals (gardening, tutoring, delivery, etc.)  

**Secondary Users**
- Small businesses & individuals posting jobs  
- NGOs, schools, and communities needing temporary help  

| Persona | Description |
|---------|-------------|
| 👤 **Thendo (21)** – Youth Job Seeker | *Pain Points:* Limited access to short-term work, needs fast income, quick/easy payments.<br>*Needs:* Simple app to find gigs nearby, instant pay, build reputation. |
| 👩 **Naledi (38)** – Small Business Owner | *Pain Points:* Hard to find reliable helpers quickly, wasted time on informal hiring.<br>*Needs:* Easy job posting, trustworthy applicants, secure payments. |

---

## 📚 Technical Documentation (PDF)

We maintain a full technical document that describes **system architecture**, **user cases**, **user roles**, **error handling**, and **operational flows** for both **Job Seekers** and **Job Posters** within the PieceJob platform.

**Download / view the PDF:**  
[📄 Full Technical Documentation (PDF)](https://drive.google.com/file/d/1or4rm4OfVE5Q4Avx_SbXSddyreCUwuOx/view?usp=sharing)

### 🧱 System Behaviors
- **Job Order Creation:** Represents accepted contract between poster and seeker.  
- **Job Completion Flow:** Seeker marks job as complete → poster confirms → triggers payment and rating process.  
- **Error Handling:** Missing inputs and failed submissions trigger contextual UI prompts rather than crashes.  


---

## 🌟 Core Features
### For Job Seekers
- Profile creation (skills, photo, experience)  
- Browse nearby gigs (filter by distance, pay, category)  
- One-tap apply  
- **Instant payments** via FNB eWallet / PayShap  
- Ratings & reviews to build credibility  
- **SmartSave**: automatically save % of earnings  

### For Job Posters
- Post a job in < 2 minutes  
- View applicants & choose best fit  
- Secure instant payments  
- Rate and review workers  

---

## ⚙️ General App Features
- **Offline / low-data usage** for entry-level smartphones  
- Clean, simple UI for non-tech-savvy users  
- **Chatbot / WhatsApp integration** for support & updates  
- Rewards: Airtime/data incentives for engagement  
- **FNB integration**: eWallet, PayMe, PayShap, financial literacy tips  

---

## 📖 User Stories & Flows
**Job Seeker Story**  
“As a job seeker, I want to see local gigs so I can apply quickly and start earning money today.”  
**Flow:** Sign Up → Add Profile → Browse Jobs → Apply → Get Hired → Complete Job → Instant Payment → Review → SmartSave  

**Job Poster Story**  
“As a job poster, I want to quickly post a job and choose from applicants so I can get help fast.”  
**Flow:** Sign Up → Post Job → View Applicants → Select Candidate → Job Done → Pay Instantly → Review  

---

## ✏️ Wireframes & UI Sketches
**Job Seeker Screens**
- Login / Sign Up  
- Profile setup  
- Job feed  
- Job details  
- Apply button  
- Wallet & earnings (SmartSave toggle)  

**Job Poster Screens**
- Post a job form  
- Applicant list  
- Payment confirmation  
- Reviews & ratings  

---

## 🛠 Tech Stack
**Frontend:** Next.js, TailwindCSS  
**Backend:** Java SpringBoot  
**Database:** PostgreSQL, Supabase    
**Auth:** OAuth 2.0 (Google, )  

---

## 🚀 MVP (Minimum Viable Product)
- Job posting (title, pay, location, description)  
- Job feed & apply function  
- Basic profile creation  
- Ratings system  
- Mock payments (simulate instant pay)  
- OAuth 2.0 login (Google, Facebook, or custom)  

---

## ✅ Final MVP Feature List
**Job Seeker**
- Sign up / profile  
- Browse & apply  
- Get paid instantly  
- Earnings dashboard  

**Job Poster**
- Post jobs quickly  
- Review applicants  
- Pay instantly  
- Ratings & reviews  

---

## 🔮 Post-MVP Features
- AI-based job matching  
- Live notifications  
- Chat between posters & seekers  
- Advanced wallet features (budgeting, savings goals)  
- Airtime/data rewards  
- Insurance & worker protection  

---

## 📌 Project Status
- **Stage:** Planning & Documentation  
- **Next Step:** Build MVP with **Next.js + SpringBoot + PostgreSQL + FNB API + OAuth 2.0 integration**  

