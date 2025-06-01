import { Component, OnInit, Inject, PLATFORM_ID, Injectable } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { RouterLink, RouterModule } from '@angular/router';
import { TagService } from '../../service/tag.service';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';



interface TagRequestDTO {
  id?: number;
  nome: string; 
}

interface UiTag extends TagRequestDTO {
  isOpen: boolean;
  descricao: string;
  isSelected: boolean; // controlar o status do checkbox
}

@Component({
  selector: 'app-detalhes-habilidades',
  imports: [HeaderComponent, FooterComponent, RouterModule, CommonModule, FormsModule],
  standalone: true,
  templateUrl: './detalhes-habilidades.component.html',
  styleUrls: ['./detalhes-habilidades.component.scss'],
  providers: [TagService]
})

export class DetalhesHabilidadesComponent implements OnInit {
  tags: UiTag[] = [];
  habilidadesSelecionadas: string[] = [];

  
  public TAG_DESCRICAO: Record<number, string> = {
    1: 'Atividades relacionadas à organização, planejamento e gestão de projetos, equipes e recursos, garantindo a eficiência e o bom funcionamento das operações.',
    2: 'Ações voltadas para o apoio e desenvolvimento de indivíduos, famílias e comunidades em situação de vulnerabilidade, promovendo inclusão social e bem-estar.',
    3: 'Serviços e iniciativas que visam a promoção da saúde, prevenção de doenças, cuidados médicos e bem-estar físico e mental da população.',
    4: 'Estratégias e atividades para angariar fundos, parcerias e recursos, além de promover a imagem e os projetos da organização para o público.',
    5: 'Criação, desenvolvimento e produção de bens artesanais, artísticos ou manufaturados, utilizando habilidades manuais e criativas.',
    6: 'Preparo, distribuição e organização de refeições e alimentos para comunidades, grupos específicos ou eventos, visando a segurança alimentar e nutricional.',
    7: 'Atividades de ensino, treinamento, orientação e compartilhamento de conhecimentos, visando o desenvolvimento pessoal e profissional de indivíduos.',
    8: 'Organização e participação em atividades esportivas, culturais, artísticas e de lazer, promovendo a integração, o desenvolvimento e a qualidade de vida.',
    9: 'Criação e disseminação de conteúdo, gestão de redes sociais, produção de materiais informativos e relacionamento com a mídia para divulgar a causa.',
    10: 'Trabalhos de construção, reforma, reparo e manutenção de estruturas, edifícios, equipamentos e espaços físicos.',
    11: 'Iniciativas e projetos focados na proteção do meio ambiente, conservação de recursos naturais, sustentabilidade e educação ambiental.',
    12: 'Atividades de cuidado, acompanhamento e suporte a pessoas que necessitam de atenção especial, como idosos, crianças, pessoas com deficiência ou em recuperação.',
    13: 'Ações voltadas para o resgate, cuidado, proteção e bem-estar de animais, incluindo adoção, tratamento e conscientização.',
    14: 'Suporte e desenvolvimento de soluções tecnológicas, manutenção de sistemas, redes e equipamentos, e capacitação em ferramentas digitais.',
    15: 'Planejamento, organização e execução de eventos, reuniões, workshops e outras atividades, garantindo a logística e o bom andamento.',
    16: 'Serviços de tradução de documentos, interpretação simultânea ou consecutiva, e facilitação da comunicação entre diferentes idiomas e culturas.'
  };
  // ********************************************************************

  constructor(
    private tagService: TagService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.carregarTags();
    }
  }

  carregarTags(): void {
    this.tagService.buscarTodos().subscribe({
      next: (data: TagRequestDTO[]) => {
        this.tags = data.map(tag => ({
          ...tag,
          isOpen: false,
          // buscar a descrição.
          descricao: this.TAG_DESCRICAO[tag.id ?? 0] || 'Descrição não disponível.',
          isSelected: false

        }));
        console.log('Tags carregadas:', this.tags);
      },
      error: (err) => {
        console.error('Erro ao carregar tags:', err);
      }
    });
  }
  adicionarHabilidades(): void {
    const tagsSelecionadas = this.tags.filter(tag => tag.isSelected);
    this.habilidadesSelecionadas = tagsSelecionadas.map(tag => tag.nome);
    console.log('Habilidades selecionadas:', this.habilidadesSelecionadas);
    alert('Habilidades adicionadas com sucesso!');
  }
  toggle(tag: UiTag): void {
    tag.isOpen = !tag.isOpen;
  }
}