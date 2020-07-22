provider "google" {
  project = "itemservice"
  credentials = file("itemservice-gcloud.json")
}


resource "google_project_service" "run" {
  service = "run.googleapis.com"
}

resource "google_cloud_run_service" "my-service" {
  name     = "itemservice"
  location = "us-central1"

  template {
    spec {
      containers {
        image = "gcr.io/itemservice/itemservice:debug"
        resources {
          limits = {
            memory = "1024Mi"
          }
        }
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }

  depends_on = [google_project_service.run]
}

resource "google_cloud_run_service_iam_member" "allUsers" {
  service  = google_cloud_run_service.my-service.name
  location = google_cloud_run_service.my-service.location
  role     = "roles/run.invoker"
  member   = "allUsers"
}

output "url" {
  value = "${google_cloud_run_service.my-service.status[0].url}"
}
